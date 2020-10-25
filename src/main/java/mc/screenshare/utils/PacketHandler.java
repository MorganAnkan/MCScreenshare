package mc.screenshare.utils;

import mc.screenshare.MCScreenshare;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class PacketHandler {
    public static int port = 4444;
    public static Socket socketa;
    private static PrintWriter out = null;
    private static Color[][] colors = null;

    public static boolean isConnected() {
        if(socketa == null) return false;
        return socketa.isConnected();
    }

    public static void start() throws Exception {
        ServerSocket server;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new Exception("[MCScreenshare] ERROR: Could not listen on port "+port);
        }

        new Thread(() -> {
            System.out.println("Waiting for client ...");
            Socket socket = null;

            BufferedInputStream bis = null;
            try {
                socket = server.accept();
                System.out.println("Client accepted");
                socketa = socket;
                if (socket == null) return;

                out = new PrintWriter(socket.getOutputStream(), true);
                bis = new BufferedInputStream(socket.getInputStream());
            } catch(Exception err) {
                err.printStackTrace();
            }
            try {
                if(socket == null) return;
                DataInputStream stream = new DataInputStream(socket.getInputStream());
                byte[] header_bytes = new byte[4];
                int header_byte_count = stream.read(header_bytes, 0, header_bytes.length);
                if (header_byte_count != header_bytes.length) {
                    stream.close();
                    return;
                }
                ByteBuffer buffer = ByteBuffer.wrap(header_bytes);
                int size = buffer.getInt();

                byte[] data = new byte[size - header_bytes.length];
                int count = stream.read(data);
                if (count != (size - header_bytes.length)) {
                    stream.close();
                    return;
                }
                buffer = ByteBuffer.wrap(data);
                int width = buffer.getInt();
                int height = buffer.getInt(4);
                int offset = 8;

                colors = new Color[height][width];

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int r = buffer.get(offset++);
                        int g = buffer.get(offset++);
                        int b = buffer.get(offset++);
                        //System.out.println("r "+ (r+128)+ " g "+(g+128)+" b " + (b+128) );
                        Color color = new Color(r + 128, g + 128, b + 128);
                        colors[y][x] = color;
                    }
                }
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static Color[][] getColors() {
        return colors;
    }

    public static int getPort() {
        return port;
    }

    public static void sendMessage(String message) {
        System.out.println("Sending "+message);
        out.println(message);
    }
}
