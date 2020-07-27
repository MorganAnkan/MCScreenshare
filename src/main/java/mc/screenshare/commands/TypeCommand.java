package mc.screenshare.commands;

import mc.screenshare.utils.PacketHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Base64;

public class TypeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PacketHandler.sendMessage("{\"text\":\""+Base64.getEncoder().encodeToString(String.join(" ", args).getBytes())+"\",\"type\":\"type\"}");
        return true;
    }
}
