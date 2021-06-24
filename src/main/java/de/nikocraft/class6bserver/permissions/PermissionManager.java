//PACKAGE
package de.nikocraft.class6bserver.permissions;


//IMPORTS

//Nikocraft
import de.nikocraft.class6bserver.Main;
import de.nikocraft.class6bserver.permissions.enums.PlayerRank;
import de.nikocraft.class6bserver.utils.Config;

//Bukkit
import org.bukkit.entity.Player;

//Java
import java.util.ArrayList;


//PERMISSION MANAGER CLASS
public class PermissionManager {

    //VARIABLES

    //The permission config (from main)
    private final Config config;


    //CONSTRUCTOR
    public PermissionManager() {

        //Get the permission config from main
        config = Main.instance.getPermissionConfig();

    }


    //METHODS

    //Get all extra permissions of a player
    public ArrayList<String> getPlayerExtraPermissions(Player player) {

        //If extra permission list contains in config
        if (config.getConfig().contains("player.uuids." + player.getUniqueId().toString() + ".extra_permissions")) {
            //Return the permission list from config
            return (ArrayList<String>) config.getConfig().getStringList("player.uuids." + player.getUniqueId().toString() + ".extra_permissions");
        }

        //Return new empty arraylist
        return new ArrayList<>();

    }

    //Get all permissions of a rank
    public ArrayList<String> getRankPermissions(PlayerRank rank) {

        //If rank permission list contains in config
        if (config.getConfig().contains("rank." + rank.getRankName() + ".permissions")) {
            //Return the permission list from config
            return (ArrayList<String>) config.getConfig().getStringList("rank." + rank.getRankName() + ".permissions");
        }

        //Return new empty arraylist
        return new ArrayList<>();

    }

    //Add extra permission to a player
    public boolean addPlayerExtraPermission(Player player, String permission) {

        //If the permission already contains, return false
        if (getPlayerExtraPermissions(player).contains(permission)) return false;

        //Get player permission list
        ArrayList<String> permissions = getPlayerExtraPermissions(player);

        //Add the new permission
        permissions.add(permission);

        //Set the permission list to config
        config.getConfig().set("player.uuids." + player.getUniqueId().toString() + ".extra_permissions", permissions);

        //Save the configuration to update changes
        config.save();

        //Return true
        return true;

    }

    //Remove extra permission to a player
    public boolean removePlayerExtraPermission(Player player, String permission) {

        //If the permission doesn't contains, return false
        if (!getPlayerExtraPermissions(player).contains(permission)) return false;

        //Get player permission list
        ArrayList<String> permissions = getPlayerExtraPermissions(player);

        //Remove the new permission
        permissions.remove(permission);

        //Set the permission list to config
        config.getConfig().set("player.uuids." + player.getUniqueId().toString() + ".extra_permissions", permissions);

        //Save the configuration to update changes
        config.save();

        //Return true
        return true;

    }

    //Set the rank of a player
    public boolean setPlayerRank(Player player, PlayerRank rank) {
        return true;
    }
    //TODO

}
