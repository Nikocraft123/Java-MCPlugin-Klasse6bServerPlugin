//PACKAGE
package de.nikocraft.class6bserver.ui.scoreboards;


//IMPORTS

//Nikocraft
import de.nikocraft.class6bserver.ui.scoreboards.enums.EntryName;

//Bukkit
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


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

    //The array with all scores of the scoreboard
    private String[] scores;

    //The final empty slot array
    private final String[] empty_scores;


    //CONSTRUCTOR
    public ScoreboardBuilder(Player player, String displayName, int size) {

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

        //Create the score array
        scores = new String[size];

        //Create the empty slot array
        empty_scores = new String[15];
        empty_scores[0] = ChatColor.DARK_PURPLE.toString();
        empty_scores[1] = ChatColor.DARK_GRAY.toString();
        empty_scores[2] = ChatColor.DARK_GREEN.toString();
        empty_scores[3] = ChatColor.DARK_AQUA.toString();
        empty_scores[4] = ChatColor.DARK_BLUE.toString();
        empty_scores[5] = ChatColor.DARK_RED.toString();
        empty_scores[6] = ChatColor.LIGHT_PURPLE.toString();
        empty_scores[7] = ChatColor.GRAY.toString();
        empty_scores[8] = ChatColor.GOLD.toString();
        empty_scores[9] = ChatColor.AQUA.toString();
        empty_scores[10] = ChatColor.YELLOW.toString();
        empty_scores[11] = ChatColor.RED.toString();
        empty_scores[12] = ChatColor.BLUE.toString();
        empty_scores[13] = ChatColor.BLACK.toString();
        empty_scores[14] = ChatColor.GREEN.toString();

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

    //Update a score
    public void updateScore(String score, int slot) {

        //Remove the old score
        removeScore(slot);

        //Set the new score
        setScore(score, slot);

    }

    //Remove a score
    public void removeScore(int slot) {

        //Remove the score from scoreboard
        scoreboard.resetScores(getScore(slot));

    }

    //Set a score
    public void setScore(String score, int slot) {

        //Define score
        String s;

        //If the score is empty, set the score to the empty score value
        if (score.equals("")) s = empty_scores[slot];
        else s = score;

        //Set the score in the array
        scores[slot] = s;

        //Set the score in the scoreboard objective
        objective.getScore(s).setScore(slot);

    }

    //Get a score
    public String getScore(int slot) {

        //Return the score from array
        return scores[slot];

    }


    //GETTERS

    //The scoreboard object
    public Scoreboard getScoreboard() { return scoreboard; }

    //The display objective of the scoreboard
    public Objective getObjective() { return objective; }

    //The owner player of the scoreboard
    public Player getPlayer() { return player; }

    //The display name of the scoreboard
    public String getDisplayName() { return displayName; }

    //The array with all scores of the scoreboard
    public String[] getScores() { return scores; }


    //SETTERS

    //The display name of the scoreboard
    public void setDisplayName(String displayName) { this.displayName = displayName; }

}
