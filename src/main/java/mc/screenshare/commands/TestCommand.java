package mc.screenshare.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class TestCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("yup executed args: "+Arrays.toString(args)+" ("+args.length+")");
        return true;
    }
}
