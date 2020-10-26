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

public class PacketHandler extends Thread {
    public static int port = 4444;
    public static Socket socketa;
    private static PrintWriter out = null;
    private static Color[][] colors = null;

    public static boolean isConnected() {
        if(socketa == null) return false;
        return socketa.isConnected();
    }

    @Override
    public void run() {
        startlol();
    }

    public static void startlol() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("[MCScreenshare] ERROR: Could not listen on port "+port);
        }
        if(server == null) return;
            System.out.println("Waiting for client ...");
            Socket socket = null;

            BufferedInputStream bis = null;
            try {
                socket = server.accept();
                System.out.println("Client accepted");
                socketa = socket;
                if (socket == null) return;

                out = new PrintWriter(socket.getOutputStream(), true);

                DataInputStream stream = new DataInputStream(socket.getInputStream());
                parsePacket(stream);
            } catch(Exception err) {
                err.printStackTrace();
            }
    }

    public static void parsePacket(DataInputStream stream) {
        try {
            byte[] header_bytes = new byte[4];
            int header_byte_count = stream.read(header_bytes, 0, header_bytes.length);
            if (header_byte_count != header_bytes.length) {
                //stream.close();
                System.out.println("bad packet recieved");
                parsePacket(stream);
                return;
            }
            ByteBuffer buffer = ByteBuffer.wrap(header_bytes);
            int size = buffer.getInt();
            if(size < header_bytes.length + 1) {
                parsePacket(stream);
                return;
            }
            byte[] data = new byte[size - header_bytes.length];
            int count = stream.read(data);
            if (count != (size - header_bytes.length)) {
                //stream.close();
                System.out.println("bad packet recieved");
                parsePacket(stream);
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
            parsePacket(stream);
            //stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                stream.close();
                if(isConnected()) socketa.close();
                Thread.currentThread().interrupt();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
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
