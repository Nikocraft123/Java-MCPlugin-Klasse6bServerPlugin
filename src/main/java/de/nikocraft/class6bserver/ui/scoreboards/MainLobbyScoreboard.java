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

    //The scores values
    private String score13;
    private String score5;
    private String score3;
    private String score2;


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
        setScore("", 14);
        score13 = ChatColor.AQUA.toString() + ChatColor.ITALIC + "Dein Rank:" + ChatColor.RESET + " " + Main.getInstance().getPermissionManager().getPlayerRank(getPlayer()).getColoredName();
        setScore(score13, 13);
        setScore(" ", 12);
        setScore(ChatColor.YELLOW + " Herzlich Willkommen auf dem ", 11);
        setScore(ChatColor.YELLOW + " Server der Klasse 6b!       ", 10);
        setScore("  ", 9);
        setScore(ChatColor.YELLOW + " Für Hilfe, gebe '/info' in  ", 8);
        setScore(ChatColor.YELLOW + " den Chat ein. Viel Spaß!    ", 7);
        setScore("   ", 6);
        score5 = ChatColor.DARK_RED.toString() + ChatColor.BOLD + " Vorsicht BETA Server!";
        setScore(score5, 5);
        setScore("    ", 4);
        score3 = ChatColor.AQUA.toString() + ChatColor.ITALIC + "Aktive Spieler:" + ChatColor.GOLD + " " + Bukkit.getOnlinePlayers().size();
        setScore(score3, 3);
        score2 = ChatColor.AQUA.toString() + ChatColor.ITALIC + "Erreichte Ziele:" + ChatColor.GOLD + " " + 0;
        setScore(score2, 2);
        setScore("     ", 1);
        try { setScore(ChatColor.DARK_PURPLE + InetAddress.getLocalHost().getHostAddress(), 0); }
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
        removeScore(score13);
        score13 = ChatColor.AQUA.toString() + ChatColor.ITALIC + "Dein Rank:" + ChatColor.RESET + " " + Main.getInstance().getPermissionManager().getPlayerRank(getPlayer()).getColoredName();
        setScore(score13, 13);

        removeScore(score5);
        if (time % 8 <= 3) score5 = ChatColor.DARK_RED.toString() + ChatColor.BOLD + " Vorsicht BETA Server!";
        else score5 = ChatColor.RED.toString() + ChatColor.BOLD + " Vorsicht BETA Server!";
        setScore(score5, 5);

        removeScore(score3);
        score3 = ChatColor.AQUA.toString() + ChatColor.ITALIC + "Aktive Spieler:" + ChatColor.GOLD + " " + Bukkit.getOnlinePlayers().size();
        setScore(score3, 3);

        removeScore(score2);
        score2 = ChatColor.AQUA.toString() + ChatColor.ITALIC + "Erreichte Ziele:" + ChatColor.GOLD + " " + 0;
        setScore(score2, 2);

    }

}
