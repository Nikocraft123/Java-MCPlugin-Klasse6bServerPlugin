//PACKAGE
package de.nikocraft.class6bserver.permissions.enums;


//IMPORTS

//Bukkit
import org.bukkit.ChatColor;


//PLAYER PERMISSION RANK ENUM
public enum PlayerRank {

    //ENUMS
    Guest("guest", 0, ChatColor.GRAY),
    Default("default", 1, ChatColor.YELLOW),
    VIP("vip", 2, ChatColor.GREEN),
    Operator("op", 3, ChatColor.GOLD),
    Admin("admin", 4, ChatColor.RED);


    //VARIABLES

    //The name of the rank
    private final String rankName;

    //The ID of the rank
    private final int rankId;

    //The full name of the rank
    private final String fullRankName;

    //The color of the rank
    private final ChatColor color;


    //CONSTRUCTOR
    PlayerRank(String rankName, int rankId, ChatColor color) {

        //Set variables
        this.rankName = rankName;
        this.rankId = rankId;
        this.color = color;
        this.fullRankName = name();

    }


    //STATIC METHODS

    //Get a player rank from unknown input
    public static PlayerRank fromUnknownInput(String input) {

        try {
            //Try to convert the input to an integer
            int intInput = Integer.parseInt(input);

            //Than return the value from the from ID method
            return fromRankId(intInput);
        }
        catch (NumberFormatException ignored) {}

        //Loop for each rank
        for (PlayerRank rank : PlayerRank.values()) {
            //If the rank founded, return it
            if (rank.getFullRankName().equalsIgnoreCase(input)) return rank;
            if (rank.getRankName().equalsIgnoreCase(input)) return rank;
        }

        //Return null
        return null;

    }

    //Get a player rank from id
    public static PlayerRank fromRankId(int rankId) {

        //Loop for each rank
        for (PlayerRank rank : PlayerRank.values()) {
            //If the rank founded, return it
            if (rank.getRankId() == rankId) return rank;
        }

        //Return guest rank
        return PlayerRank.Guest;

    }

    //Get a player rank from name
    public static PlayerRank fromRankName(String rankName) {

        //Loop for each rank
        for (PlayerRank rank : PlayerRank.values()) {
            //If the rank founded, return it
            if (rank.getRankName().equals(rankName)) return rank;
        }

        //Return guest rank
        return PlayerRank.Guest;

    }

    //Get a player rank from full name
    public static PlayerRank fromFullRankName(String fullRankName) {

        //Loop for each rank
        for (PlayerRank rank : PlayerRank.values()) {
            //If the rank founded, return it
            if (rank.getFullRankName().equals(fullRankName)) return rank;
        }

        //Return guest rank
        return PlayerRank.Guest;

    }


    //METHODS

    //Get colored full name of the rank
    public String getColoredName() {
        return getColor() + getFullRankName();
    }


    //GETTERS

    //The name of the rank
    public String getRankName() {
        return rankName;
    }

    //The ID of the rank
    public int getRankId() {
        return rankId;
    }

    //The full name of the rank
    public String getFullRankName() {
        return fullRankName;
    }

    //The color of the rank
    public ChatColor getColor() {
        return color;
    }

}
