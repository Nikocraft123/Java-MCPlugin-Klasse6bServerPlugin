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

    //Set all player teams
    public void setAllPlayerTeams() {

        //Set for each online player the teams
        Bukkit.getOnlinePlayers().forEach(this::setPlayerTeams);

    }

    //Set player teams
    public void setPlayerTeams(Player player) {

        //Get the scoreboard of the player
        Scoreboard scoreboard = player.getScoreboard();

        //Get all rank teams
        Team guests = scoreboard.getTeam("4guests");
        Team defaults = scoreboard.getTeam("3defaults");
        Team vips = scoreboard.getTeam("2vips");
        Team ops = scoreboard.getTeam("1ops");
        Team admins = scoreboard.getTeam("0admins");

        //If a team doesn't exist, register it
        if (guests == null) guests = scoreboard.registerNewTeam("4guests");
        if (defaults == null) defaults = scoreboard.registerNewTeam("3defaults");
        if (vips == null) vips = scoreboard.registerNewTeam("2vips");
        if (ops == null) ops = scoreboard.registerNewTeam("1ops");
        if (admins == null) admins = scoreboard.registerNewTeam("0admins");

        //Set the prefix of all teams
        admins.setPrefix(PlayerRank.Admin.getColor() + "Admin" + ChatColor.GRAY + " | ");
        admins.setColor(PlayerRank.Admin.getColor());
        ops.setPrefix(PlayerRank.Operator.getColor() + "Operator" + ChatColor.GRAY + " | ");
        ops.setColor(PlayerRank.Operator.getColor());
        vips.setPrefix(PlayerRank.VIP.getColor() + "VIP" + ChatColor.GRAY + " | ");
        vips.setColor(PlayerRank.VIP.getColor());
        defaults.setPrefix(PlayerRank.Default.getColor() + "Default" + ChatColor.GRAY + " | ");
        defaults.setColor(PlayerRank.Default.getColor());
        guests.setPrefix(PlayerRank.Guest.getColor() + "Guest" + ChatColor.GRAY + " | ");
        guests.setColor(PlayerRank.Guest.getColor());
        
        //For in all online players
        for (Player target : Bukkit.getOnlinePlayers()) {

            if (Main.getInstance().getPermissionManager().getPlayerRank(target) == PlayerRank.Admin) admins.addEntry(target.getName());
            if (Main.getInstance().getPermissionManager().getPlayerRank(target) == PlayerRank.Operator) ops.addEntry(target.getName());
            if (Main.getInstance().getPermissionManager().getPlayerRank(target) == PlayerRank.VIP) vips.addEntry(target.getName());
            if (Main.getInstance().getPermissionManager().getPlayerRank(target) == PlayerRank.Default) defaults.addEntry(target.getName());
            if (Main.getInstance().getPermissionManager().getPlayerRank(target) == PlayerRank.Guest) guests.addEntry(target.getName());

        }

    }

}
