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

import java.util.ArrayList;

public class DrawCommand implements CommandExecutor {


    private static Location playerLoc;
    private static World currentWorld;

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
}
