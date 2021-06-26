//PACKAGE
package de.nikocraft.class6bserver.ui;


//IMPORTS

//Nikocraft
import de.nikocraft.class6bserver.Main;
import de.nikocraft.class6bserver.ui.scoreboards.MainLobbyScoreboard;

//Bukkit
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


//SCOREBOARD MANAGER CLASS
public class ScoreboardManager {

    //VARIABLES

    //The owner player of the scoreboard manager
    private final Player player;

    //The main lobby scoreboard
    private MainLobbyScoreboard mainLobbyScoreboard;

    //The manager scheduler ID
    private int runID;


    //CONSTRUCTOR
    public ScoreboardManager(Player player) {

        //Set the player
        this.player = player;

        //Create a new main lobby scoreboard for the player
        mainLobbyScoreboard = new MainLobbyScoreboard(player);

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

                //If the player is offline, close the scheduler
                if (!player.isOnline()) Bukkit.getScheduler().cancelTask(runID);

                //Update the scoreboard
                mainLobbyScoreboard.update();

            }

        }, 4, 4);

    }

}
