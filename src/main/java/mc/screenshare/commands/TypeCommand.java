package mc.screenshare.commands;

import mc.screenshare.utils.PacketHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TypeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage("u must have some args lmao");
            return true;
        }
        if(!PacketHandler.isConnected()) {
            sender.sendMessage("hmmm not connected");
        } else {
            sender.sendMessage("attempting to make the client type: "+String.join(" ", args));
            PacketHandler.sendMessage("{\"text\":\"" +String.join(" ", args).replaceAll("\"", "\\\"") + "\",\"type\":\"type\"}");
        }
        return true;
    }
}
