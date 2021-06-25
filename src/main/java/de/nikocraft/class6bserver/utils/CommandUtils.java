//PACKAGE
package de.nikocraft.class6bserver.utils;


//IMPORTS

//Bukkit
import org.bukkit.ChatColor;

//Java
import java.util.ArrayList;


//STATIC COMMAND UTILS CLASS
public class CommandUtils {

    //STATIC METHODS

    //Format a tap completer list
    public static ArrayList<String> formatTapCompleterList(ArrayList<String> list, String[] args) {

        //If no arguments contains, return null
        if (args.length == 0) return null;

        //Define a result list
        ArrayList<String> result = new ArrayList<>();

        //Get the current argument
        String currentArg = args[args.length - 1].toLowerCase();

        //Loop in the list
        for (String c : list) {

            //Lower the complete
            String complete = c.toLowerCase();

            //If the complete starts with the current argument, add it to the result list
            if (complete.startsWith(currentArg)) result.add(c);

        }

        //Return the result
        return result;

    }

    //Get the prefix of the command for the console
    public static String getConsolePrefix() {

        //Return prefix string
        return "[Command] ";

    }

    //Get the prefix of the command for the chat
    public static String getChatPrefix() {

        //Return prefix string
        return ChatColor.GRAY + "[" + ChatColor.BLUE + ChatColor.BOLD + "Command" + ChatColor.RESET +
                ChatColor.GRAY + "] " + ChatColor.WHITE;

    }

}
