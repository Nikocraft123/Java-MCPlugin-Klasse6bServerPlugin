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

    //Set the header and footer of the tablist of a new player
    public void setTablistHeaderFooter(Player player) {

        //Create string for server ip
        String ip;

        //Try to get the server ip
        //try { ip = InetAddress.getLocalHost().getHostAddress(); }
        //catch (UnknownHostException e) { ip = "Unknown Ip Address"; }
        ip = Bukkit.getIp();

        //Set the header of the tablist
        player.setPlayerListHeader(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "          " + ChatColor.GRAY +
                " [ " + ChatColor.GOLD + ChatColor.BOLD + "KLASSEN SERVER 6B" + ChatColor.GRAY + " ] " + ChatColor.DARK_GRAY +
                ChatColor.STRIKETHROUGH + "          " + ChatColor.RESET + "\n" +
                ChatColor.RED + ChatColor.BOLD + "!Vorsicht BETA Server!" + ChatColor.RESET + "\n");

        //Set the footer of the tablist
        player.setPlayerListFooter("\n" + ChatColor.DARK_PURPLE + "Server IP: " + ChatColor.ITALIC + ip);

    }

    //Set all player teams
    public void setAllPlayerTeams() {

        //Set for each online player the teams
        for (Player player : Bukkit.getOnlinePlayers()) setPlayerTeams(player);

    }

    //Set player teams
    public void setPlayerTeams(Player player) {

        //Get the scoreboard of the player
        Scoreboard scoreboard = player.getScoreboard();

        //For in all player ranks
        for (PlayerRank rank : PlayerRank.values()) {

            //Get the team from rank
            Team team = scoreboard.getTeam(rank.getRankId() + rank.getRankName());

            //If the team doesn't exist, register it
            if (team == null) team = scoreboard.registerNewTeam(rank.getRankId() + rank.getRankName());

            //Set the prefix of the team
            team.setPrefix(ChatColor.GRAY + "[" + rank.getColoredName() + ChatColor.GRAY + "] ");

            //Set the color of the team
            team.setColor(rank.getColor());

            //Loop for all online players
            for (Player target : Bukkit.getOnlinePlayers()) {
                //If the rank of the target is equals the current team, add it
                if (Main.getInstance().getPermissionManager().getPlayerRank(target) == rank) team.addEntry(target.getName());
            }

        }

    }

}
