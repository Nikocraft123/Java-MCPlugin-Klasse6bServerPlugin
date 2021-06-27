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
    private int time = 0;


    //CONSTRUCTOR
    public MainLobbyScoreboard(Player player) {

        //Initialize the scoreboard
        super(player, "   Klasse 6b - Hauptlobby   ");

    }


    //OVERRIDE METHODS

    //Create the scoreboard
    @Override
    public void create() {

        //Build scoreboard scores
        setFixScore("", 14);
        setUpdateScore(ChatColor.AQUA.toString() + ChatColor.ITALIC + "Dein Rank:" + ChatColor.RESET + " " + Main.getInstance().getPermissionManager().getPlayerRank(getPlayer()).getColoredName(), 13, 0);
        setFixScore(" ", 12);
        setFixScore(ChatColor.YELLOW + " Herzlich Willkommen auf dem ", 11);
        setFixScore(ChatColor.YELLOW + " Server der Klasse 6b!       ", 10);
        setFixScore("  ", 9);
        setFixScore(ChatColor.YELLOW + " Für Hilfe, gebe '/info' in  ", 8);
        setFixScore(ChatColor.YELLOW + " den Chat ein. Viel Spaß!    ", 7);
        setFixScore("   ", 6);
        setUpdateScore(ChatColor.DARK_RED.toString() + ChatColor.BOLD + " Vorsicht BETA Server!", 5, 1);
        setFixScore("    ", 4);
        setUpdateScore(ChatColor.AQUA.toString() + ChatColor.ITALIC + "Aktive Spieler:" + ChatColor.GOLD + " " + Bukkit.getOnlinePlayers().size(), 3, 2);
        setUpdateScore(ChatColor.AQUA.toString() + ChatColor.ITALIC + "Erreichte Ziele:" + ChatColor.GOLD + " " + 0, 2, 3);
        setFixScore("     ", 1);
        try { setFixScore(ChatColor.DARK_PURPLE + InetAddress.getLocalHost().getHostAddress(), 0); }
        catch (UnknownHostException e) { e.printStackTrace(); }

    }

    //Update the scoreboard
    @Override
    public void update() {

        //Update scoreboard display name
        updateDisplayName(time);

        //Update scores of the scoreboard
        updateScores();

        //Update time
        if (time >= 16) time = 0;
        time++;

    }


    //METHODS

    //Update scores of the scoreboard
    private void updateScores() {

        //Update scoreboard scores
        setUpdateScore(ChatColor.AQUA.toString() + ChatColor.ITALIC + "Dein Rank:" + ChatColor.RESET + " " + Main.getInstance().getPermissionManager().getPlayerRank(getPlayer()).getColoredName(), 13, 0);
        if (time % 8 <= 3) setUpdateScore(ChatColor.DARK_RED.toString() + ChatColor.BOLD + " Vorsicht BETA Server!", 5, 1);
        else setUpdateScore(ChatColor.DARK_RED.toString() + ChatColor.BOLD + " Vorsicht BETA Server!", 5, 2);
        setUpdateScore(ChatColor.AQUA.toString() + ChatColor.ITALIC + "Aktive Spieler:" + ChatColor.GOLD + " " + Bukkit.getOnlinePlayers().size(), 3, 2);
        setUpdateScore(ChatColor.AQUA.toString() + ChatColor.ITALIC + "Erreichte Ziele:" + ChatColor.GOLD + " " + 0, 2, 3);

    }

}
