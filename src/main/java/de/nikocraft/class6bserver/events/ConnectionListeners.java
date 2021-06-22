//PACKAGE
package de.nikocraft.class6bserver.events;

//IMPORTS

//Bukkit
import de.nikocraft.class6bserver.Main;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

//CONNECTION LISTENERS CLASS
public class ConnectionListeners implements Listener {

    //EVENT HANDLER METHODS

    //Called, if a player logged in server
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {



    }

    //Called, if a player joined
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        //TEMP TEST
        event.getPlayer().spigot().sendMessage(Main.getChatPrefix(new TextComponent("Herzlich Willkommen auf dem Server!")));

    }

    //Called, if a player quited
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {



    }

}
