//PACKAGE
package de.nikocraft.class6bserver.commands;


//IMPORTS

//Bukkit
import de.nikocraft.class6bserver.utils.CommandUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;


//WARP COMMAND CLASS
public class WarpCommand implements CommandExecutor, TabCompleter {

    //OVERRIDE METHODS

    //Called, if the command sent
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Is the sender a player
        boolean isPlayer = sender instanceof Player;

        //If no arguments contains
        if (args.length == 0) {

            //Send message to sender
            if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.RED + "Argumente vermisst!");
            else sender.sendMessage(CommandUtils.getConsolePrefix() + "Argumente vermisst!");

            //Return false
            return false;

        }

        //Switch in argument 1
        switch (args[0].toLowerCase()) {

            case "world":
            case "w":

                //Handle world warp command
                return handleWorldCommand(sender, command, label, args);

            case "player":
            case "p":

                //Handle player warp command
                return handlePlayerCommand(sender, command, label, args);

            case "location":
            case "l":

                //Handle location warp command
                return handleLocationCommand(sender, command, label, args);

            default:

                //Send message to sender
                if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.RED + "Ungültiges erstes Argument!");
                else sender.sendMessage(CommandUtils.getConsolePrefix() + "Ungültiges erstes Argument!");

                //Return false
                return false;

        }

    }

    //Called, on typing command in the chat
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }


    //METHODS

    //Handle world warp command
    private boolean handleWorldCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

    //Handle player warp command
    private boolean handlePlayerCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

    //Handle location warp command
    private boolean handleLocationCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

}
