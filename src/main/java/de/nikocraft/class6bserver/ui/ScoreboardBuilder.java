//PACKAGE
package de.nikocraft.class6bserver.ui;


//IMPORTS

//Bukkit
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;


//ABSTRACT SCOREBOARD BUILDER CLASS
public abstract class ScoreboardBuilder {

    //VARIABLES

    //The scoreboard object
    private final Scoreboard scoreboard;

    //The display objective of the scoreboard
    private final Objective objective;

    //The owner player of the scoreboard
    private final Player player;

    //The display name of the scoreboard
    private String displayName;


    //CONSTRUCTOR
    public ScoreboardBuilder(Player player, String displayName) {

        //Set the player and display name
        this.player = player;
        this.displayName = displayName;

        //Set the scoreboard of the player to a new scoreboard, if the current scoreboard is the main scoreboard
        if (player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())) player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        //Get the scoreboard from player
        scoreboard = player.getScoreboard();

        //Unregister the display objective of the scoreboard, if it is not null
        if (scoreboard.getObjective("display") != null) scoreboard.getObjective("display").unregister();

        //Get and register the display objective from scoreboard
        objective = scoreboard.registerNewObjective("display", "dummy", ChatColor.GOLD.toString() + ChatColor.BOLD + displayName);

        //Set the display slot of the objective
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        //Create the scoreboard
        create();

    }


    //ABSTRACT METHODS

    //Create the scoreboard
    public abstract void create();

    //Update the scoreboard
    public abstract void update();


    //METHODS

    //Update scoreboard display name
    public void updateDisplayName(int updateTime) {

        if (updateTime % 16 == 10) objective.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + displayName);
        if (updateTime % 16 == 2) objective.setDisplayName(ChatColor.WHITE.toString() + ChatColor.BOLD + displayName);

    }

    //Set a score of the scoreboard
    public void setScore(String content, int score) {
        objective.getScore(content).setScore(score);
    }

    //Remove a score from the scoreboard
    public void removeScore(String content) {
        scoreboard.resetScores(content);
    }


    //GETTERS

    //The scoreboard object
    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    //The display objective of the scoreboard
    public Objective getObjective() {
        return objective;
    }

    //The owner player of the scoreboard
    public Player getPlayer() {
        return player;
    }

    //The display name of the scoreboard
    public String getDisplayName() {
        return displayName;
    }


    //SETTERS

    //The display name of the scoreboard
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
