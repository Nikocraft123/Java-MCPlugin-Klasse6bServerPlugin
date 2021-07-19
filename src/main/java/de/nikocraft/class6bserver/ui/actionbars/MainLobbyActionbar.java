//PACKAGE
package de.nikocraft.class6bserver.ui.actionbars;


//IMPORTS

//Bukkit
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


//MAIN LOBBY ACTIONBAR CLASS
public class MainLobbyActionbar extends ActionbarBuilder {

    //CONSTRUCTOR
    public MainLobbyActionbar(Player player) {

        //Initialize actionbar
        super(player);

    }


    //OVERRIDE METHODS

    //Update the actionbar
    @Override
    public void update() {

        //Update the display text
        if (getUpdateTime() % 50 < 10) setDisplayText(new TextComponent(ChatColor.GOLD + "Herzlich Willkommen"));
        else if (getUpdateTime() % 50 < 20) setDisplayText(new TextComponent(ChatColor.GOLD + "auf dem Server der Klasse 6b!"));
        else if (getUpdateTime() % 50 < 30) setDisplayText(new TextComponent(ChatColor.GOLD + "Schau dich doch mal um!"));
        else if (getUpdateTime() % 50 < 40) setDisplayText(new TextComponent(ChatColor.GOLD + "Viel Spaß wünscht"));
        else setDisplayText(new TextComponent(ChatColor.GOLD + "die Serververwaltung [Reik, Till, Nikolas]!"));

        //Send the actionbar to the player
        send();

        //Tick the update time
        tick();

    }

}
