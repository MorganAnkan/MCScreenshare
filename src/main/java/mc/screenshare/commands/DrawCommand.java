package mc.screenshare.commands;

import mc.screenshare.MCScreenshare;
import mc.screenshare.utils.PacketHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawCommand implements CommandExecutor, Listener {

    public static int getRainbow(int speed, float saturation, float brightness, int offset) {
        double hue = Math.ceil((System.currentTimeMillis() + offset) / speed);
        hue %= 360.0;
        return Color.getHSBColor((float) (hue / 360.0), saturation, brightness).getRGB();
    }

    static public class Renderer extends MapRenderer {
        @Override
        public void render(MapView map, MapCanvas canvas, Player player) {
            BufferedImage image = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.getGraphics();
            Color[][] pixels = PacketHandler.getColors();
            if(pixels == null || pixels.length == 0) {
                g.setColor(Color.RED);
                g.fillRect(0, 0, 128, 128);
                return;
            }

            for (int y = 0; y < pixels.length; y++) {
                for (int x = 0; x < pixels[0].length; x++) {
                    g.setColor(pixels[y][x]);
                    g.fillRect(x, y, Math.min(x + 1, pixels[0].length), Math.min(y + 1, pixels.length));
                }
            }
            canvas.drawImage(0, 0, image);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            sender.sendMessage("You can't execute this from console!");
        } else {
            Player player = ((Player) sender);
            sender.sendMessage("Starting socket server on port " + PacketHandler.getPort() + "...");
            boolean cancel = false;
            try {
                PacketHandler.start();
            } catch (Exception e) {
                sender.sendMessage("§cFailed to start socket server... (check console for more details)");
                e.printStackTrace();
                cancel = true;
            }
            if(cancel) return true;
            sender.sendMessage("Checking for nearby itemframes...");
            Bukkit.getScheduler().runTask(MCScreenshare.getPlugin(MCScreenshare.class), new Runnable() {
                @Override
                public void run() {
                    Location loc = player.getLocation();
                    for (Entity entity : player.getNearbyEntities(loc.getX(), loc.getY(), loc.getZ())) {
                        if(entity instanceof ItemFrame) {
                            ItemStack item = ((ItemFrame) entity).getItem();
                            if(item.getType().equals(Material.FILLED_MAP)) {
                                sender.sendMessage("Using itemframe with filled map at X: " +
                                        entity.getLocation().getBlockX()+", Y: "+entity.getLocation().getBlockY() + ", Z: " + entity.getLocation().getBlockZ());
                                MapView mapView = ((MapMeta) item.getItemMeta()).getMapView();
                                if (mapView != null) {
                                    mapView.getRenderers().forEach(mapView::removeRenderer);
                                    mapView.addRenderer(new Renderer());
                                }
                            }
                        }
                    }
                }
            });
            return true;
        }
        return true;
    }
}
