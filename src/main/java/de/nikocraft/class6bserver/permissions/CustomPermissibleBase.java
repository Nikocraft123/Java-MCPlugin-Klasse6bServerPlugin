//PACKAGE
package de.nikocraft.class6bserver.permissions;


//IMPORTS

//Bukkit
import de.nikocraft.class6bserver.Main;
import de.nikocraft.class6bserver.permissions.enums.PlayerRank;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissibleBase;

import java.util.ArrayList;


//CUSTOM PERMISSIBLE BASE CLASS
public class CustomPermissibleBase extends PermissibleBase {

    //VARIABLES

    //The player of the base
    private final Player player;


    //CONSTRUCTOR
    public CustomPermissibleBase(Player player) {

        //Initialize the base with the player
        super(player);

        //Set the player
        this.player = player;

    }


    //OVERRIDE METHODS

    //Check for permission of the player
    @Override
    public boolean hasPermission(String permission) {

        //Get extra permissions of the player
        ArrayList<String> extraPermissions = Main.getInstance().getPermissionManager().getPlayerExtraPermissions(player);

        //Get the rank of the player
        PlayerRank rank = Main.getInstance().getPermissionManager().getPlayerRank(player);

        //If the extra permission list contains not permission, return false
        if (extraPermissions.contains("!" + permission)) return false;

        //If the rank contains the permission, return true
        if (Main.getInstance().getPermissionManager().getRankPermissions(rank).contains(permission)) return true;

        //If the rank or the extra permission list has *, return true
        if (Main.getInstance().getPermissionManager().getRankPermissions(rank).contains("*") || extraPermissions.contains("*")) return true;

        //Return, is the permission in extra permission list
        return extraPermissions.contains(permission);

    }

}
