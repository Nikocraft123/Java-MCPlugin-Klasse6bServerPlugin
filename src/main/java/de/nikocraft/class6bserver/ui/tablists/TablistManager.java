//PACKAGE
package de.nikocraft.class6bserver.ui.tablists;


//IMPORTS

//Bukkit
import de.nikocraft.class6bserver.Main;
import de.nikocraft.class6bserver.permissions.enums.PlayerRank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

//Java
import java.net.InetAddress;
import java.net.UnknownHostException;


//TABLIST MANAGER CLASS
public class TablistManager {

    //METHODS

    //Set the tablist of a new player
    public void setTablist(Player player) {

        //Create string for server ip
        String ip;

        //Try to get the server ip
        try { ip = InetAddress.getLocalHost().getHostAddress(); }
        catch (UnknownHostException e) { ip = "Unknown Ip Address"; }


        //Set the Header of the tablist
        player.setPlayerListHeaderFooter(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "          " + ChatColor.GRAY +
                " [ " + ChatColor.GOLD + ChatColor.BOLD + "KLASSEN SERVER 6B" + ChatColor.GRAY + " ] " + ChatColor.DARK_GRAY +
                ChatColor.STRIKETHROUGH + "          " + ChatColor.RESET + "\n" +
                ChatColor.RED + ChatColor.BOLD + "!Vorsicht Beta Server!" + ChatColor.RESET + "\n","\n" + ChatColor.DARK_PURPLE + "Server IP: " + ChatColor.ITALIC + ip);

    }

    //Set a player teams
    public void setPlayerTeams(Player player) {

        //Get the scoreboard of the player
        Scoreboard scoreboard = player.getScoreboard();

        //Get all rank teams
        Team guests = scoreboard.getTeam("guests");
        Team defaults = scoreboard.getTeam("defaults");
        Team vips = scoreboard.getTeam("vips");
        Team ops = scoreboard.getTeam("ops");
        Team admins = scoreboard.getTeam("admins");

        //If a team doesn't exist, register it
        if (guests == null) guests = scoreboard.registerNewTeam("guests");
        if (defaults == null) defaults = scoreboard.registerNewTeam("defaults");
        if (vips == null) vips = scoreboard.registerNewTeam("vips");
        if (ops == null) ops = scoreboard.registerNewTeam("ops");
        if (admins == null) admins = scoreboard.registerNewTeam("admins");

        //Set the prefix of all teams
        admins.setPrefix(ChatColor.RED + "Admin" + ChatColor.GRAY + " | ");
        admins.setColor(ChatColor.RED);
        
        //For in all online players
        for (Player target : Bukkit.getOnlinePlayers()) {

            if (Main.getInstance().getPermissionManager().getPlayerRank(target) == PlayerRank.Admin) admins.addEntry(target.getName());
            else ops.addEntry(target.getName());

        }

    }

}
