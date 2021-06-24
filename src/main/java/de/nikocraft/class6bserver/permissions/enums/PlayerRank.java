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
    private String rankName;

    //The ID of the rank
    private int rankId;


    //CONSTRUCTOR
    PlayerRank(String rankName, int rankId) {

        //Set variables
        this.rankName = rankName;
        this.rankId = rankId;

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
