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
        config = Main.getInstance().getPermissionConfig();

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

        //Remove the permission
        permissions.remove(permission);

        //Set the permission list to config
        config.getConfig().set("player.uuids." + player.getUniqueId().toString() + ".extra_permissions", permissions);

        //Save the configuration to update changes
        config.save();

        //Return true
        return true;

    }

    //Add permission to a rank
    public boolean addRankPermission(PlayerRank rank, String permission) {

        //If the permission already contains, return false
        if (getRankPermissions(rank).contains(permission)) return false;

        //Get rank permission list
        ArrayList<String> permissions = getRankPermissions(rank);

        //Add the new permission
        permissions.add(permission);

        //Set the permission list to config
        config.getConfig().set("rank." + rank.getRankName() + ".permissions", permissions);

        //Save the configuration to update changes
        config.save();

        //Return true
        return true;

    }

    //Remove permission to a rank
    public boolean removeRankPermission(PlayerRank rank, String permission) {

        //If the permission doesn't contains, return false
        if (!getRankPermissions(rank).contains(permission)) return false;

        //Get rank permission list
        ArrayList<String> permissions = getRankPermissions(rank);

        //Remove the permission
        permissions.remove(permission);

        //Set the permission list to config
        config.getConfig().set("rank." + rank.getRankName() + ".permissions", permissions);

        //Save the configuration to update changes
        config.save();

        //Return true
        return true;

    }

    //Set the rank of a player
    public boolean setPlayerRank(Player player, PlayerRank rank) {

        //If the player has a rank in config
        if (config.getConfig().contains("player.uuids." + player.getUniqueId().toString() + ".rank")) {
            //Return false, if the current rank is equals to the new rank
            if (config.getConfig().getInt("player.uuids." + player.getUniqueId().toString() + ".rank") == rank.getRankId()) return false;
        }

        //Set the rank
        config.getConfig().set("player.uuids." + player.getUniqueId().toString() + ".rank", rank.getRankId());

        //Save the configuration to update changes
        config.save();

        //Return true
        return true;

    }

    //Get the rank of a player
    public PlayerRank getPlayerRank(Player player) {

        //If the player has a rank in config
        if (config.getConfig().contains("player.uuids." + player.getUniqueId().toString() + ".rank")) {
            //Return the player rank from config
            return PlayerRank.fromRankId(config.getConfig().getInt("player.uuids." + player.getUniqueId().toString() + ".rank"));
        }

        //Set the rank to guest
        config.getConfig().set("player.uuids." + player.getUniqueId().toString() + ".rank", PlayerRank.Guest.getRankId());

        //Save the configuration to update changes
        config.save();

        //Return guest rank
        return PlayerRank.Guest;

    }

}
