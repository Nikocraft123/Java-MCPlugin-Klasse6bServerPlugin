//PACKAGE
package de.nikocraft.class6bserver.permissions;


//IMPORTS

//Bukkit
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissibleBase;


//CUSTOM PERMISSIBLE BASE CLASS
public class CustomPermissibleBase extends PermissibleBase {

    //VARIABLES

    //The player of the base
    private Player player;


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
    public boolean hasPermission(String inName) {
        //TODO
        return super.hasPermission(inName);
    }

}
