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


    //CONSTRUCTOR
    PlayerRank(String rankName, int rankId) {

        //Set variables
        this.rankName = rankName;
        this.rankId = rankId;

    }


    //STATIC METHODS

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


    //GETTERS

    //The name of the rank
    public String getRankName() {
        return rankName;
    }

    //The ID of the rank
    public int getRankId() {
        return rankId;
    }

}
