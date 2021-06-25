//PACKAGE
package de.nikocraft.class6bserver.commands;


//IMPORTS

//Nikocraft
import de.nikocraft.class6bserver.Main;
import de.nikocraft.class6bserver.permissions.enums.PlayerRank;
import de.nikocraft.class6bserver.utils.CommandUtils;

//Bukkit
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

//Java
import java.util.ArrayList;
import java.util.List;


//PERMISSION COMMAND EXECUTOR CLASS
public class PermissionCommand implements CommandExecutor, TabCompleter {

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

            case "player":
            case "p":

                //Handel player permission command
                return handelPlayerCommand(sender, command, label, args);

            case "rank":
            case "r":

                //Handel rank permission command
                return handelRankCommand(sender, command, label, args);

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

        //Define a result list
        ArrayList<String> result = new ArrayList<>();

        //Switch in the count of arguments
        switch (args.length) {

            case 1:
                //Add completes to result
                result.add("player");
                result.add("rank");

                break;
            case 2:
                //Switch in argument 1
                switch (args[0].toLowerCase()) {
                    case "player":
                    case "p":
                        //For in all online players
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            //Add player name to result
                            result.add(player.getName());
                        }
                        break;
                    case "rank":
                    case "r":
                        //For in all player ranks
                        for (PlayerRank rank : PlayerRank.values()) {
                            //Add rank full name to result
                            result.add(rank.getFullRankName());
                        }
                        break;
                }

                break;
            case 3:
                //Add completes to result
                result.add("add");
                result.add("remove");

                //Add rank, if the argument 1 is player (p)
                if (args[0].equalsIgnoreCase("player") || args[0].equalsIgnoreCase("p")) result.add("rank");

                break;
            case 4:
                //Switch in argument 3
                switch (args[2].toLowerCase()) {

                    case "add":
                        //Add completes to result
                        result.add("server.");
                        result.add("bukkit.");
                        result.add("spigot.");
                        result.add("minecraft.");
                        result.add("*");

                        break;
                    case "remove":
                        //If the argument 1 is player (p)
                        if (args[0].equalsIgnoreCase("player") || args[0].equalsIgnoreCase("p")) {
                            //If the player doesn't found, break
                            if (Bukkit.getPlayer(args[1]) == null) break;

                            //For in all permissions of the player
                            for (String perm : Main.getInstance().getPermissionManager().getPlayerExtraPermissions(Bukkit.getPlayer(args[1]))) {
                                //Add permission to result
                                result.add(perm);
                            }
                        }
                        //Else If the argument 1 is rank (r)
                        else if (args[0].equalsIgnoreCase("rank") || args[0].equalsIgnoreCase("r")) {
                            //If the rank doesn't found, break
                            if (PlayerRank.fromUnknownInput(args[1].toLowerCase()) == null) break;

                            //For in all permissions of the player
                            for (String perm : Main.getInstance().getPermissionManager().getRankPermissions(PlayerRank.fromUnknownInput(args[1].toLowerCase()))) {
                                //Add permission to result
                                result.add(perm);
                            }
                        }

                        break;
                    case "rank":
                        //Add rank, if the argument 1 is player (p)
                        if (args[0].equalsIgnoreCase("player") || args[0].equalsIgnoreCase("p")) {
                            //For in all player ranks
                            for (PlayerRank rank : PlayerRank.values()) {
                                //Add rank full name to result
                                result.add(rank.getFullRankName());
                            }
                        }

                        break;
                }
        }

        //Return the formatted result
        return CommandUtils.formatTapCompleterList(result, args);

    }


    //METHODS

    //Handel player permission command
    private boolean handelPlayerCommand(CommandSender sender, Command command, String label, String[] args) {

        //Is the sender a player
        boolean isPlayer = sender instanceof Player;

        //If the argument 2 missed
        if (args.length == 1) {

            //Send message to sender
            if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.RED + "Zweites Argument vermisst!");
            else sender.sendMessage(CommandUtils.getConsolePrefix() + "Zweites Argument vermisst!");

            //Return false
            return false;

        }

        //Get the targeted player
        Player target = Bukkit.getPlayer(args[1]);

        //If the targeted player doesn't founded
        if (target == null) {

            //Send message to sender
            if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.RED + "Der Spieler '" + args[1] + "' konnte nicht gefunden werden!");
            else sender.sendMessage(CommandUtils.getConsolePrefix() + "Der Spieler '" + args[1] + "' konnte nicht gefunden werden!");

            //Return false
            return false;

        }

        //If only 2 arguments contains
        if (args.length == 2) {

            //Define string for permissions of the target
            String perms = "";

            //If the sender is a player
            if (isPlayer) {
                //Load all permissions of the targeted player
                for (String perm : Main.getInstance().getPermissionManager().getPlayerExtraPermissions(target)) {
                    perms += ChatColor.DARK_GRAY + "- " + ChatColor.YELLOW + perm + "\n";
                }

                //Send message to sender
                sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.AQUA + "Rechteinformation von '" + target.getName() + "'" +
                        ChatColor.GRAY + ":\n \n" + ChatColor.AQUA + "Rank: " + ChatColor.YELLOW +
                        Main.getInstance().getPermissionManager().getPlayerRank(target).getFullRankName() + "\n \n" +
                        ChatColor.AQUA + "Extra Rechte:\n" + perms + "\n ");
            }
            else {
                //Load all permissions of the targeted player
                for (String perm : Main.getInstance().getPermissionManager().getPlayerExtraPermissions(target)) {
                    perms += "- " + perm + "\n";
                }

                //Send message to sender
                sender.sendMessage(CommandUtils.getConsolePrefix() + "Rechteinformation von '" + target.getName() + "':\n \nRank: " +
                        Main.getInstance().getPermissionManager().getPlayerRank(target).getFullRankName() + "\n \nExtra Rechte:\n" + perms + "\n ");
            }

            //Return true
            return true;

        }

        //Switch in argument 3
        switch (args[2].toLowerCase()) {

            case "add":
                //If the argument 4 missed
                if (args.length < 4) {

                    //Send message to sender
                    if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.RED + "Viertes Argument vermisst!");
                    else sender.sendMessage(CommandUtils.getConsolePrefix() + "Viertes Argument vermisst!");

                    //Return false
                    return false;

                }

                //Add the permission to player (If failed)
                if (!Main.getInstance().getPermissionManager().addPlayerExtraPermission(target, args[3])) {

                    //Send message to sender
                    if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.RED + "Dieser Spieler besitzt bereits dieses Recht!");
                    else sender.sendMessage(CommandUtils.getConsolePrefix() + "Dieser Spieler besitzt bereits dieses Recht!");

                    //Return false
                    return false;

                }

                //Send message to sender
                if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.GREEN + "Das Recht '" + args[3] + "' wurde erfolgreich dem Spieler '" + target.getName() + "' hinzugefügt!");
                else sender.sendMessage(CommandUtils.getConsolePrefix() + "Das Recht '" + args[3] + "' wurde erfolgreich dem Spieler '" + target.getName() + "' hinzugefügt!");

                break;
            case "remove":
                //If the argument 4 missed
                if (args.length < 4) {

                    //Send message to sender
                    if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.RED + "Viertes Argument vermisst!");
                    else sender.sendMessage(CommandUtils.getConsolePrefix() + "Viertes Argument vermisst!");

                    //Return false
                    return false;

                }

                //Remove the permission to player (If failed)
                if (!Main.getInstance().getPermissionManager().removePlayerExtraPermission(target, args[3])) {

                    //Send message to sender
                    if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.RED + "Dieser Spieler besitzt dieses Recht nicht!");
                    else sender.sendMessage(CommandUtils.getConsolePrefix() + "Dieser Spieler besitzt dieses Recht nicht!");

                    //Return false
                    return false;

                }

                //Send message to sender
                if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.GREEN + "Das Recht '" + args[3] + "' wurde erfolgreich dem Spieler '" + target.getName() + "' entfernt!");
                else sender.sendMessage(CommandUtils.getConsolePrefix() + "Das Recht '" + args[3] + "' wurde erfolgreich dem Spieler '" + target.getName() + "' entfernt!");

                break;
            case "rank":
                //If the argument 4 missed
                if (args.length < 4) {

                    //Send message to sender
                    if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.RED + "Viertes Argument vermisst!");
                    else sender.sendMessage(CommandUtils.getConsolePrefix() + "Viertes Argument vermisst!");

                    //Return false
                    return false;

                }

                //If the rank doesn't found
                if (PlayerRank.fromUnknownInput(args[1].toLowerCase()) == null) {

                    //Send message to sender
                    if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.RED + "Ungültiger Rank!");
                    else sender.sendMessage(CommandUtils.getConsolePrefix() + "Ungültiger Rank!");

                    //Return false
                    return false;

                }

                //Set the rank of the player (If failed)
                if (!Main.getInstance().getPermissionManager().setPlayerRank(target, PlayerRank.fromUnknownInput(args[1].toLowerCase()))) {

                    //Send message to sender
                    if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.RED + "Dieser Spieler hat bereits diesen Rank!");
                    else sender.sendMessage(CommandUtils.getConsolePrefix() + "Dieser Spieler hat bereits diesen Rank!");

                    //Return false
                    return false;

                }

                //Send message to sender
                if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.GREEN + "Der Rank '" + PlayerRank.fromUnknownInput(args[1].toLowerCase()).getFullRankName() + "' wurde erfolgreich dem Spieler '" + target.getName() + "' verliehen!");
                else sender.sendMessage(CommandUtils.getConsolePrefix() + "Der Rank '" + PlayerRank.fromUnknownInput(args[1].toLowerCase()).getFullRankName() + "' wurde erfolgreich dem Spieler '" + target.getName() + "' verliehen!");

                break;
            default:

                //Send message to sender
                if (isPlayer) sender.sendMessage(CommandUtils.getChatPrefix() + ChatColor.RED + "Ungültiges drittes Argument!");
                else sender.sendMessage(CommandUtils.getConsolePrefix() + "Ungültiges drittes Argument!");

                //Return false
                return false;

        }

        //Return true
        return true;

    }

    //Handel rank permission command
    private boolean handelRankCommand(CommandSender sender, Command command, String label, String[] args) {

        //Is the sender a player
        boolean isPlayer = sender instanceof Player;

        //TEMP
        //TODO
        sender.sendMessage("[Noch in Entwicklung]");

        //Return true
        return true;

    }

}
