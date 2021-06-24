//PACKAGE
package de.nikocraft.class6bserver;

//IMPORTS

//Bukkit
import de.nikocraft.class6bserver.events.ConnectionListeners;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

//MAIN CLASS
public final class Main extends JavaPlugin {

    //VARIABLES

    //The instance of the Main
    public static Main instance;


    //OVERRIDE METHODS

    //Called, if the Plugin is loading
    @Override
    public void onLoad() {

        //Set the instance to this
        instance = this;

    }

    //Called, if the Plugin is enabling
    @Override
    public void onEnable() {

        //Register Listeners
        Bukkit.getPluginManager().registerEvents(new ConnectionListeners(), this);

    }

    //Called, if the Plugin is disabling
    @Override
    public void onDisable() {



    }


    //METHODS

    //Get the prefix of the Plugin for the console
    public static String getConsolePrefix() {

        //Return prefix string
        return "[Klasse 6b - Server Plugin] ";

    }

    //Get the prefix of the Plugin for the chat
    public static TextComponent getChatPrefix(TextComponent message) {

        //Build prefix
        TextComponent prefix = new TextComponent(ChatColor.GRAY + "[");
        TextComponent prefixName = new TextComponent(ChatColor.GREEN.toString() + ChatColor.BOLD + "S. P." + ChatColor.RESET);
        prefixName.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(ChatColor.GREEN.toString() + ChatColor.ITALIC + "Klasse 6b - Server Plugin")));
        prefix.addExtra(prefixName);
        prefix.addExtra(ChatColor.GRAY + "] " + ChatColor.RESET + ChatColor.YELLOW);

        //Add message to prefix
        prefix.addExtra(message);

        //Return prefix component
        return prefix;

    }


    //GETTER

    //The instance of the Main
    public static Main getInstance() {
        return instance;
    }

}
