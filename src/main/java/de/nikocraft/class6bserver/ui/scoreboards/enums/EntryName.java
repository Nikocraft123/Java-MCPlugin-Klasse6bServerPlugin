//PACKAGE
package de.nikocraft.class6bserver.ui.scoreboards.enums;


//IMPORTS

//Bukkit
import org.bukkit.ChatColor;


//ENTRY NAME ENUM
public enum EntryName {

    //ENUMS
    ENTRY_0(0, ChatColor.DARK_PURPLE.toString()),
    ENTRY_1(1, ChatColor.DARK_GRAY.toString()),
    ENTRY_2(2, ChatColor.DARK_GREEN.toString()),
    ENTRY_3(3, ChatColor.DARK_AQUA.toString()),
    ENTRY_4(4, ChatColor.DARK_BLUE.toString()),
    ENTRY_5(5, ChatColor.DARK_RED.toString()),
    ENTRY_6(6, ChatColor.LIGHT_PURPLE.toString()),
    ENTRY_7(7, ChatColor.GRAY.toString()),
    ENTRY_8(8, ChatColor.GOLD.toString()),
    ENTRY_9(9, ChatColor.AQUA.toString()),
    ENTRY_10(10, ChatColor.YELLOW.toString()),
    ENTRY_11(11, ChatColor.RED.toString()),
    ENTRY_12(12, ChatColor.BLUE.toString()),
    ENTRY_13(13, ChatColor.BLACK.toString()),
    ENTRY_14(14, ChatColor.GREEN.toString());


    //VARIABLES

    //The entry
    private final int entry;

    //The name of the entry
    private final String name;


    //CONSTRUCTOR
    private EntryName(int entry, String name) {

        //Set entry and entry name
        this.entry = entry;
        this.name = name;

    }
    
    
    //STATIC METHODS
    
    //Get a entry name from score
    public static EntryName fromScore(int score) {
        
        //For in all entries
        for (EntryName entryName : EntryName.values()) {
            //If the entry founded, return it
            if (entryName.getEntry() == score) return entryName;
        }

        //Return null
        return null;
        
    }


    //GETTERS

    //The entry
    public int getEntry() {
        return entry;
    }

    //The name of the entry
    public String getName() {
        return name;
    }

}
