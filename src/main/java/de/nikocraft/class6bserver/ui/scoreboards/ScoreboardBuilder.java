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

    //Set a score of the scoreboard, that is fix
    public void setFixScore(String content, int score) {

        //Set the score
        objective.getScore(content).setScore(score);

    }

    //Set a score of the scoreboard, that can update
    public void setUpdateScore(String content, int score, int updateScore) {

        //Get the team from score
        Team team = getTeam(updateScore);

        //If the team doesn't found, return
        if (team == null) return;

        //Set the prefix of the team to the content
        team.setPrefix(content);

        //Show the score
        showScore(score, updateScore);

    }

    //Remove a score from the scoreboard
    public void removeScore(int score) {

        //Hide the score
        hideScore(score);

    }

    //Show a score
    private void showScore(int score, int updateScore) {

        //Get the entry name
        EntryName name = EntryName.fromScore(updateScore);

        //If the entry doesn't found, return
        if (name == null) return;

        //If the score has an entry with this name, return
        if (objective.getScore(name.getName()).isScoreSet()) return;

        //Set the score
        objective.getScore(name.getName()).setScore(score);

    }

    //Hide a score
    private void hideScore(int score) {

        //Get the entry name
        EntryName name = EntryName.fromScore(score);

        //If the entry doesn't found, return
        if (name == null) return;

        //If the score hasn't an entry with this name, return
        if (!objective.getScore(name.getName()).isScoreSet()) return;

        //Reset the score
        scoreboard.resetScores(name.getName());

    }

    //Get a team by an score
    private Team getTeam(int score) {

        //Get entry with the score
        EntryName entryName = EntryName.fromScore(score);

        //Return null, if the entry name is null
        if (entryName == null) return null;

        //Get the team with the entry name
        Team team = scoreboard.getEntryTeam(entryName.getName());

        //If the team founded, return it
        if (team != null) return team;

        //Register a new team
        team = scoreboard.registerNewTeam(entryName.name());

        //Add the entry to the team
        team.addEntry(entryName.getName());

        //Return the new team
        return team;

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
