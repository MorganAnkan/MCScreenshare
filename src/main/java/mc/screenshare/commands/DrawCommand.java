package mc.screenshare.commands;

import imgscalr.Scalr;
import mc.screenshare.MCScreenshare;
import mc.screenshare.utils.TimerUtil;
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
    private static BufferedImage currentImg = null;
    private static int maxWidth = 128;
    private static int maxHeight = 128;
    private static int renderFPS = 1;
    private static TimerUtil timer = new TimerUtil();
    public static boolean stop = false;

    static public class Renderer extends MapRenderer {
        @Override
        public void render(MapView map, MapCanvas canvas, Player player) {
            if(currentImg == null) {
                BufferedImage image = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
                Graphics g = image.getGraphics();
                g.setColor(Color.BLUE);
                g.fillRect(0, 0, 128, 128);
                canvas.drawImage(0, 0, image);
                return;
            }

            canvas.drawImage(0, 0, currentImg);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            sender.sendMessage("You can't execute this from console!");
        } else {
            if(args.length > 1) {
                int fps = -1;
                try {
                    fps = Integer.parseInt(args[0]);
                } catch(Exception ignored) {}
                if(fps < 1) fps = 1;
                //if(fps > 20) fps = 20;
                renderFPS = fps;
            }
            Player player = ((Player) sender);
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
            stop = false;
            loop();
        }
        return true;
    }

    public static boolean isStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        DrawCommand.stop = stop;
    }

    private static void loop() {
       new Thread() {
           @Override
           public void run() {
               timer.start(1000/renderFPS);
               while(!stop) {
                   if(timer.isDone()) {
                       try {
                           Robot robot = new Robot();
                           int width = Toolkit.getDefaultToolkit().getScreenSize().width;
                           int height = Toolkit.getDefaultToolkit().getScreenSize().height;

                           Image image = robot.createScreenCapture(new Rectangle(0, 0, width, height));

                           BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                           Graphics g = bi.createGraphics();
                           g.drawImage(image, 0, 0, width, height, null);

                           double ratio = Math.min((double) maxWidth / width, (double) maxHeight / height);

                           currentImg = Scalr.resize(bi, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, (int) Math.round(width * ratio), (int) Math.round(height * ratio), Scalr.OP_ANTIALIAS);
                       } catch (AWTException e) {
                           e.printStackTrace();
                           this.interrupt();
                       }
                       timer.start(1000 / renderFPS);
                   }
               }
           }
       }.start();
    }

    /*new BukkitRunnable() {
                @Override
                public void run() {
                    try {
                        Robot robot = new Robot();
                        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
                        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

                        Image image = robot.createScreenCapture(new Rectangle(0, 0, width, height));

                        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                        Graphics g = bi.createGraphics();
                        g.drawImage(image, 0, 0, width, height, null);

                        double ratio = Math.min((double) maxWidth / width, (double) maxHeight / height);

                        currentImg = Scalr.resize(bi, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, (int)Math.round(width * ratio), (int)Math.round(height * ratio), Scalr.OP_ANTIALIAS);
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskTimer(MCScreenshare.getPlugin(MCScreenshare.class), 0, Math.round(20f/renderFPS));*/

}
