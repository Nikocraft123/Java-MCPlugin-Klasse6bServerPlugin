//PACKAGE
package de.nikocraft.class6bserver.ui.scoreboards;


//IMPORTS

//Nikocraft
import de.nikocraft.class6bserver.Main;

//Bukkit
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

//Java
import java.util.HashMap;


//SCOREBOARD MANAGER CLASS
public class ScoreboardManager {

    //VARIABLES

    //The map with all players and there active scoreboard
    private final HashMap<Player, ScoreboardBuilder> playerScoreboards;

    //The manager scheduler ID
    private int runID;


    //CONSTRUCTOR
    public ScoreboardManager() {

        //Create the player scoreboard map
        playerScoreboards = new HashMap<>();

        //Run the manager
        run();

    }


    //METHODS

    //Run the manager
    private void run() {

        //Create the scheduler of the manager
        runID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            //Override the run method of the scheduler
            @Override
            public void run() {

                //Loop for all players in the map
                for (Player player : playerScoreboards.keySet()) {

                    //If the player is online
                    if (player.isOnline()) {

                        //Update the scoreboard of the player
                        playerScoreboards.get(player).update();

                    }
                    //Else, Remove the player and his scoreboard
                    else playerScoreboards.remove(player);

                }

            }

        }, 4, 4);

    }

    //Set the scoreboard of a player
    public void setPlayerScoreboard(Player player, ScoreboardBuilder scoreboard) {

        //Put the player in the map
        playerScoreboards.put(player, scoreboard);

    }

    //Get a scoreboard of a player
    public ScoreboardBuilder getPlayerScoreboard(Player player) {

        //Return the scoreboard of the player
        return playerScoreboards.get(player);

    }


    //GETTERS

    //The manager scheduler ID
    public int getRunID() { return runID; }

}
