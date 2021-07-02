//PACKAGE
package de.nikocraft.class6bserver.ui.actionbars;


//IMPORTS

//Bukkit
import de.nikocraft.class6bserver.Main;
import de.nikocraft.class6bserver.ui.scoreboards.ScoreboardBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

//Java
import java.util.HashMap;


//ACTIONBAR MANAGER CLASS
public class ActionbarManager {

    //VARIABLES

    //The map with all players and there active actionbar
    private final HashMap<Player, ActionbarBuilder> playerActionbars;

    //The manager scheduler ID
    private int runID;


    //CONSTRUCTOR
    public ActionbarManager() {

        //Create the player actionbars map
        playerActionbars = new HashMap<>();

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
                for (Player player : playerActionbars.keySet()) {

                    //If the player is online
                    if (player.isOnline()) {

                        //Update the actionbar of the player
                        playerActionbars.get(player).update();

                    }
                    //Else, Remove the player and his actionbar
                    else playerActionbars.remove(player);

                }

            }

        }, 4, 4);

    }

    //Set the actionbar of a player
    public void setPlayerActionbar(Player player, ActionbarBuilder actionbar) {

        //Put the player in the map
        playerActionbars.put(player, actionbar);

    }

    //Get a actionbar of a player
    public ActionbarBuilder getPlayerActionbar(Player player) {

        //Return the actionbar of the player
        return playerActionbars.get(player);

    }


    //GETTERS

    //The manager scheduler ID
    public int getRunID() { return runID; }

}
