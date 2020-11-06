package mc.screenshare;

import mc.screenshare.commands.DrawCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCScreenshare extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[MCScreenshare] Initialized");

        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new DrawCommand(), this);
        this.getCommand("draw").setExecutor(new DrawCommand());
        //getConfig().options().copyDefaults();
        //saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        System.out.println("[MCScreenshare] going skadoosh");
        if(!DrawCommand.isStop()) {
            DrawCommand.setStop(true);
        }
    }

    /*public static Configuration getConfigFile() {
        return Bukkit.getPluginManager().getPlugin("MCScreenshare").getConfig();
    }*/

    public static Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("MCScreenshare");
    }
}
