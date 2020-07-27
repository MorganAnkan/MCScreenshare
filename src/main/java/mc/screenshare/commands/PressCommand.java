package mc.screenshare.commands;

import mc.screenshare.utils.PacketHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PressCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage("u must have some args lmao");
            return true;
        }
        if(PacketHandler.isConnected()) {
            String key = String.join("_",args).toLowerCase();
            sender.sendMessage("attempting to press key "+key+" on the client");
            PacketHandler.sendMessage("{\"type\":\"presskey\", \"key\":\""+key+"\"}");
        }
        return true;
    }
}
