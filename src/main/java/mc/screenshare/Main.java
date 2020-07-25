package mc.screenshare;

import mc.screenshare.commands.DrawCommand;
import mc.screenshare.events.CreatureSpawn;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.Configuration;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("[MCScreenshare] Initialized");
        /* //command listerners etc skidded from util
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new PlayerChat(), this);
        pm.registerEvents(new PlayerCommandPreprocess(), this);
        pm.registerEvents(new SignChange(), this);
        pm.registerEvents(new PreLogin(), this);
        pm.registerEvents(new ServerPing(), this);*/
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new CreatureSpawn(), this);
        this.getCommand("draw").setExecutor(new DrawCommand());
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
    public static Configuration getConfigFile() {
        return Bukkit.getPluginManager().getPlugin("MCScreenshare").getConfig();
    }

}
