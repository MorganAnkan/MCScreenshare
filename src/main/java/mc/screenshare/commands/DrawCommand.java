package mc.screenshare.commands;

import mc.screenshare.Main;
import mc.screenshare.utils.PacketHandler;
import mc.screenshare.utils.Pixel;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.awt.*;
import java.util.ArrayList;

public class DrawCommand implements CommandExecutor, Listener {
    private static Location playerLoc;
    private static World currentWorld;
    private static Pixel maxPixel;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(maxPixel == null || currentWorld == null || playerLoc == null) return;
        if (event.getPlayer().hasPermission("mcscreenshare.touchscreen")) {
            Location blockLoc = event.getBlock().getLocation();
            //System.out.println("blockloc: "+blockLoc.toString()+", maxpixelx+playerlocx: "+(maxPixel.getX() +
            //       playerLoc.getBlockX())+", maxpixely+playerlocz: "+(maxPixel.getY() + playerLoc.getBlockZ()));
            if(blockLoc.getBlockX() > (maxPixel.getX() + playerLoc.getBlockX()) || blockLoc.getBlockZ() > (maxPixel.getY() + playerLoc.getBlockZ())
            || blockLoc.getBlockX() < playerLoc.getBlockX() || blockLoc.getBlockZ() < playerLoc.getBlockZ() || blockLoc.getBlockY() != playerLoc.getBlockY()) return;
            PacketHandler.sendMessage("{\"x\":"+(blockLoc.getBlockX()-playerLoc.getBlockX())+",\"y\":"+(blockLoc.getBlockZ()-playerLoc.getBlockZ())+"}");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            System.out.println("Starting packet handler");
        } else {
            sender.sendMessage("Starting socketserver...\nPlease note that your current location will be the start position.");
            Player player = (Player) sender;
            playerLoc = player.getLocation();
            currentWorld = player.getWorld();
        }
        PacketHandler.safeStart();
        return true;
    }

    public static void draw(ArrayList<Pixel> pixels) {
        if(playerLoc == null || currentWorld == null) return;

        maxPixel = getMaxCoord(pixels);
        int x = playerLoc.getBlockX();
        int y = playerLoc.getBlockY();
        int z = playerLoc.getBlockZ();

        for (Pixel pixel : pixels) {
            Location locationToPlace = new Location(currentWorld, x+pixel.x, y, z+pixel.y);
            Bukkit.getScheduler().runTask(Main.getPlugin(Main.class), new Runnable() {
                @Override
                public void run() {
                    locationToPlace.getBlock().setType(pixel.getMaterial());
                }
            });
        }
    }

    private static Pixel getMaxCoord(ArrayList<Pixel> list) {
        Pixel maxCoord = new Pixel(0, 0, new Color(255, 255, 255));
        for (Pixel pixel : list) {
            if (pixel.x > maxCoord.getX() || pixel.y > maxCoord.getY()) {
                maxCoord = pixel;
            }
        }
        return maxCoord;
    }
}
