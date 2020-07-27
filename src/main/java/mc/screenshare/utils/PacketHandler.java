package mc.screenshare.utils;

import mc.screenshare.Main;
import mc.screenshare.commands.DrawCommand;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class PacketHandler {
    public static int port = Main.getConfigFile().getInt("port");
    public static Socket socketa;
    private static PrintWriter out = null;

    public static void safeStart() {
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isConnected() {
        if(socketa == null) return false;
        return socketa.isConnected();
    }

    public static void start() throws Exception {
        ServerSocket server;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new Exception("[MCScreenshare] WARN: Could not listen on port "+port);
        }

        new Thread(() -> {
            System.out.println("Waiting for client ...");
            Socket socket = null;
            try {
                socket = server.accept();
                System.out.println("Client accepted");
            } catch (IOException e) {
                e.printStackTrace();
            }
            socketa = socket;
            if(socket == null) return;
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException ignored) {
            }
            InputStream inputStream = null;
            try {
                inputStream = socket.getInputStream();
            } catch (IOException ignored) {
            }
            InputStreamReader streamReader = null;
            if (inputStream == null) return;
            streamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(streamReader);

            String line = null;
            while (true) {
                try {
                    //if(!socket.isConnected()) Thread.currentThread().stop();
                    if ((line = br.readLine()) == null) break;
                } catch (IOException ignored) {
                }
                //System.out.println("[MCScreenshare] got new message from client size: " + line.length());
                handlePacket(line);
            }
        }).start();
    }

    public static void handlePacket(String packet) {
        ArrayList<Pixel> pixels = new ArrayList<>();
        String[] splitPixels = packet.split("\\|");
        for (String pix : splitPixels) {
            String[] pixSplit = pix.split(",");
            int x = Integer.parseInt(pixSplit[0]);
            int y = Integer.parseInt(pixSplit[1]);
            Color color = Color.BLACK;
            try {
                String[] rgb = pixSplit[2].split("-");
                color = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
            } catch(Exception bad) { System.out.println("oops something bad with the color "+pixSplit[2].replaceAll("-", ","));
            }//use black color if it fails for some reason
            Pixel pixel = new Pixel(x, y, color);
            pixel.setMaterialFromColor();
            pixels.add(pixel);
        }
        DrawCommand.draw(pixels);
    }

    public static void sendMessage(String message) {
        System.out.println("Sending "+message);
        out.println(message);
    }
}
