//PACKAGE
package de.nikocraft.class6bserver.ui.scoreboards;


//IMPORTS

//Nikocraft
import de.nikocraft.class6bserver.Main;

//Bukkit
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

//Java
import java.net.InetAddress;
import java.net.UnknownHostException;


//MAIN LOBBY SCOREBOARD CLASS
public class MainLobbyScoreboard extends ScoreboardBuilder {

    //VARIABLES

    //The current scoreboard update time
    private int updateTime = 0;


    //CONSTRUCTOR
    public MainLobbyScoreboard(Player player) {

        //Initialize the scoreboard
        super(player, "   HAUPTLOBBY   ", 15);

    }


    //OVERRIDE METHODS

    //Create the scoreboard
    @Override
    public void create() {

        //Build scoreboard scores
        setScore("", 14);
        setScore(ChatColor.AQUA.toString() + ChatColor.ITALIC + "Dein Rank:" + ChatColor.RESET + " " + Main.getInstance().getPermissionManager().getPlayerRank(getPlayer()).getColoredName(), 13);
        setScore("", 12);
        setScore(ChatColor.YELLOW + " Herzlich Willkommen auf dem ", 11);
        setScore(ChatColor.YELLOW + " Server der Klasse 6b!       ", 10);
        setScore("", 9);
        setScore(ChatColor.YELLOW + " Für Hilfe, gebe '/info' in  ", 8);
        setScore(ChatColor.YELLOW + " den Chat ein. Viel Spaß!    ", 7);
        setScore("", 6);
        setScore(ChatColor.DARK_RED.toString() + ChatColor.BOLD + " !Vorsicht BETA Server!", 5);
        setScore("", 4);
        setScore(ChatColor.AQUA.toString() + ChatColor.ITALIC + "Aktive Spieler:" + ChatColor.GOLD + " " + Bukkit.getOnlinePlayers().size(), 3);
        setScore(ChatColor.AQUA.toString() + ChatColor.ITALIC + "Erreichte Ziele:" + ChatColor.GOLD + " " + 0, 2);
        setScore("", 1);
        //try { setScore(ChatColor.DARK_PURPLE + "Server IP: " + ChatColor.ITALIC + InetAddress.getLocalHost().getHostAddress(), 0); }
        //catch (UnknownHostException e) { setScore(ChatColor.DARK_PURPLE + "Server IP: " + ChatColor.ITALIC + "Unknown Ip Address", 0); }
        setScore(ChatColor.DARK_PURPLE + "Server IP: " + ChatColor.ITALIC + Bukkit.getIp(), 0);

    }

    //Update the scoreboard
    @Override
    public void update() {

        //Update scoreboard display name
        updateDisplayName(updateTime);

        //Update scores of the scoreboard
        updateScores();

        //Update time
        if (updateTime >= 16) updateTime = 0;
        updateTime++;

    }


    //METHODS

    //Update scores of the scoreboard
    private void updateScores() {

        //Update scoreboard scores
        updateScore(ChatColor.AQUA.toString() + ChatColor.ITALIC + "Dein Rank:" + ChatColor.RESET + " " + Main.getInstance().getPermissionManager().getPlayerRank(getPlayer()).getColoredName(), 13);
        if (updateTime % 8 <= 3) updateScore(ChatColor.DARK_RED.toString() + ChatColor.BOLD + " !Vorsicht BETA Server!", 5);
        else updateScore(ChatColor.RED.toString() + ChatColor.BOLD + " !Vorsicht BETA Server!", 5);
        updateScore(ChatColor.AQUA.toString() + ChatColor.ITALIC + "Aktive Spieler:" + ChatColor.GOLD + " " + Bukkit.getOnlinePlayers().size(), 3);
        updateScore(ChatColor.AQUA.toString() + ChatColor.ITALIC + "Erreichte Ziele:" + ChatColor.GOLD + " " + 0, 2);

    }

}
