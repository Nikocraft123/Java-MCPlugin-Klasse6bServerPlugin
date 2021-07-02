//PACKAGE
package de.nikocraft.class6bserver.events;


//IMPORTS

//Nikocraft
import de.nikocraft.class6bserver.Main;

//Bukkit
import de.nikocraft.class6bserver.permissions.CustomPermissibleBase;
import de.nikocraft.class6bserver.ui.actionbars.MainLobbyActionbar;
import de.nikocraft.class6bserver.ui.scoreboards.MainLobbyScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftHumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.reflect.Field;


//CONNECTION LISTENER CLASS
public class ConnectionListeners implements Listener {

    //EVENT HANDLER METHODS

    //Called, if a player logged in server
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {

        try {
            //Try to set the permissible base of the player to the custom permissible base
            Field field = CraftHumanEntity.class.getDeclaredField("perm");
            field.setAccessible(true);
            field.set(event.getPlayer(), new CustomPermissibleBase(event.getPlayer()));
            field.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            //Catch Error
            e.printStackTrace();
        }

        //Load the rank of the player
        Main.getInstance().getPermissionManager().getPlayerRank(event.getPlayer());

    }

    //Called, if a player joined
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        //Send welcome message to player
        event.getPlayer().sendMessage(Main.getChatPrefix() + ChatColor.YELLOW + ChatColor.UNDERLINE + "Herzlich Willkommen auf dem Server der Klasse 6b!\n" +
                ChatColor.YELLOW + " \nGebe '/info' in den Chat ein, um mehr Hilfe zu erhalten!\nMit Tab kannst du sehen, wer alles gerade online ist.\n \n" +
                ChatColor.BLUE + ChatColor.BOLD + "Viel Spaß und Erfolg!\n" + ChatColor.GRAY + " - " + ChatColor.DARK_PURPLE + ChatColor.ITALIC + "Die Serververwaltung [Reik, Till, Nikolas]\n ");

        //Set join message
        event.setJoinMessage(ChatColor.GRAY + ">> " + ChatColor.DARK_GREEN + ChatColor.BOLD + event.getPlayer().getName() +
                ChatColor.RESET + ChatColor.GRAY + " hat den Server betreten!");

        //Add the player to the scoreboard manager
        Main.getInstance().getScoreboardManager().setPlayerScoreboard(event.getPlayer(), new MainLobbyScoreboard(event.getPlayer()));

        //Add the player to the actionbar manager
        Main.getInstance().getActionbarManager().setPlayerActionbar(event.getPlayer(), new MainLobbyActionbar(event.getPlayer()));

        //Set the tablist of the player
        Main.getInstance().getTablistManager().setTablistHeaderFooter(event.getPlayer());
        Main.getInstance().getTablistManager().setAllPlayerTeams();

    }

    //Called, if a player quited
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        //Set quit message
        event.setQuitMessage(ChatColor.GRAY + "<< " + ChatColor.DARK_RED + ChatColor.BOLD + event.getPlayer().getName() +
                ChatColor.RESET + ChatColor.GRAY + " hat den Server verlassen!");

        //TODO

    }

}
