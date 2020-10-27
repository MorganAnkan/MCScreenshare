package mc.screenshare.utils;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class PacketHandler extends Thread {
    public static int port = 4444;
    public static Socket socketa;
    private static PrintWriter out = null;
    private static Color[][] colors = null;
    private static final byte[] CHECK = {68, 70, 68, 70, 68, 70, 68, 70};

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
        byte[] checkArray = new byte[8];
        while (true) {
            try {
                if (!isConnected() || socketa.isClosed() || socketa.isInputShutdown()) {
                    System.out.println("Lost connection with client...");
                    stream.close();
                    Thread.currentThread().interrupt();
                    return;
                }

                byte data = stream.readByte();
                System.out.println("READ: "+data);
                checkArray = shiftArray(checkArray, data);
                if (!Arrays.equals(checkArray, CHECK))
                    continue;
                else
                    System.out.println("oh wtf");
                Arrays.fill(checkArray, (byte) 0); //clears array
                ArrayList<Byte> image = new ArrayList<Byte>();
                while (!Arrays.equals(checkArray, CHECK)) {
                    data = stream.readByte();
                    checkArray = shiftArray(checkArray, data);
                    image.add(data);
                }
                System.out.println("mabe");
                for (int i = 0; i < CHECK.length; i++) {
                    image.remove(image.size() - 1); //get rid of the check data
                }

                ByteBuffer buffer = ByteBuffer.wrap(new byte[0]);
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

            } catch (Exception e) {
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
    }

    public static byte[] shiftArray(byte[] array, byte element) {
        byte[] result = new byte[array.length];
        if (array.length - 1 >= 0) System.arraycopy(array, 1, result, 0, array.length - 1);
        result[result.length - 1] = element;
        return result;
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
