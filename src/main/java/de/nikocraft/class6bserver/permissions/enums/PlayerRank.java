//PACKAGE
package de.nikocraft.class6bserver.permissions.enums;


//PLAYER PERMISSION RANK ENUM
public enum PlayerRank {

    //ENUMS
    Guest("guest", 0),
    Player("player", 1),
    VIP("vip", 2),
    Operator("op", 3),
    Admin("admin", 4);


    //VARIABLES

    //The name of the rank
    private final String rankName;

    //The ID of the rank
    private final int rankId;

    //The full name of the rank
    private final String fullRankName;


    //CONSTRUCTOR
    PlayerRank(String rankName, int rankId) {

        //Set variables
        this.rankName = rankName;
        this.rankId = rankId;
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
        catch (NumberFormatException e) {
            //Loop for each rank
            for (PlayerRank rank : PlayerRank.values()) {
                //If the rank founded, return it
                if (rank.getRankName().equals(input)) return rank;
                if (rank.getFullRankName().equals(input)) return rank;
            }
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

        //Log Error
        //TODO

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

        //Log Error
        //TODO

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

        //Log Error
        //TODO

        //Return guest rank
        return PlayerRank.Guest;

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

    public String getFullRankName() {
        return fullRankName;
    }

}
