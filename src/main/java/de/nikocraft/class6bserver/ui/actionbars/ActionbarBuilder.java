//PACKAGE
package de.nikocraft.class6bserver.ui.actionbars;


//IMPORTS

//Bukkit
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;


//ABSTRACT ACTIONBAR BUILDER CLASS
public abstract class ActionbarBuilder {

    //VARIABLES

    //The current actionbar update time
    private int updateTime = 0;

    //The current display text
    private TextComponent displayText;

    //The owner of the actionbar
    private final Player player;


    //CONSTRUCTOR
    public ActionbarBuilder(Player player) {

        //Set the player
        this.player = player;

        //Load the actionbar with the update method
        update();

    }


    //ABSTRACT METHODS

    //Update the actionbar of the player
    public abstract void update();


    //METHODS

    //Tick the update time
    public void tick() {

        //Add 1 to the update time
        updateTime++;

        //Reset the update time, if it reached 100
        if (updateTime >= 100) updateTime = 0;

    }

    //Send the actionbar to the player
    public void send() {

        //Send to player
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, displayText);

    }


    //GETTERS

    //The current actionbar update time
    public int getUpdateTime() { return updateTime; }

    //The current display text
    public TextComponent getDisplayText() { return displayText; }


    //SETTERS

    //The current display text
    public void setDisplayText(TextComponent displayText) { this.displayText = displayText; }

}
