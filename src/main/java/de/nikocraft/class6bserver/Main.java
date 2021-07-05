//PACKAGE
package de.nikocraft.class6bserver;


//IMPORTS

//Nikocraft
import de.nikocraft.class6bserver.commands.PermissionCommand;
import de.nikocraft.class6bserver.events.ConnectionListeners;
import de.nikocraft.class6bserver.permissions.PermissionManager;
import de.nikocraft.class6bserver.ui.actionbars.ActionbarManager;
import de.nikocraft.class6bserver.ui.scoreboards.ScoreboardManager;
import de.nikocraft.class6bserver.ui.tablists.TablistManager;
import de.nikocraft.class6bserver.utils.Config;

//Bukkit
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


//MAIN CLASS
public final class Main extends JavaPlugin {

    //VARIABLES

    //The instance of the main
    private static Main instance;

    //The permission manager of the permission system
    private PermissionManager permissionManager;

    //The scoreboard manager of the server
    private ScoreboardManager scoreboardManager;

    //The actionbar manager of the server
    private ActionbarManager actionbarManager;

    //The tablist manager of the server
    private TablistManager tablistManager;

    //The master configuration of the plugin
    private Config masterConfig;

    //The permission system configuration
    private Config permissionConfig;

    //The world system configuration
    private Config worldConfig;


    //OVERRIDE METHODS

    //Called, if the plugin is loading
    @Override
    public void onLoad() {

        //Set the instance to this
        instance = this;

        //Load configurations
        String configPath = "./plugins/Klasse6bServer/Configs/";
        masterConfig = new Config(configPath, "MasterConfig.yml");
        permissionConfig = new Config(configPath, "PermissionConfig.yml");
        worldConfig = new Config(configPath, "WorldConfig.yml");

    }

    //Called, if the plugin is enabling
    @Override
    public void onEnable() {

        //Register listeners
        Bukkit.getPluginManager().registerEvents(new ConnectionListeners(), this);

        //Register commands
        getCommand("permission").setExecutor(new PermissionCommand());

        //Define permission manager
        permissionManager = new PermissionManager();

        //Define scoreboard manager
        scoreboardManager = new ScoreboardManager();

        //Define actionbar manager
        actionbarManager = new ActionbarManager();

        //Define tablist manager
        tablistManager = new TablistManager();

    }

    //Called, if the plugin is disabling
    @Override
    public void onDisable() {

        //Save configurations
        masterConfig.save();
        permissionConfig.save();
        worldConfig.save();

    }


    //STATIC METHODS

    //Get the prefix of the plugin for the console
    public static String getConsolePrefix() {

        //Return prefix string
        return "[Server Plugin] ";

    }

    //Get the prefix of the plugin for the chat
    public static String getChatPrefix() {

        //Return prefix string
        return ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "Server" + ChatColor.RESET +
                ChatColor.GRAY + "] " + ChatColor.WHITE;

    }


    //GETTERS

    //The instance of the main
    public static Main getInstance() { return instance; }

    //The permission manager of the permission system
    public PermissionManager getPermissionManager() { return permissionManager; }

    //The scoreboard manager of the server
    public ScoreboardManager getScoreboardManager() { return scoreboardManager; }

    //The actionbar manager of the server
    public ActionbarManager getActionbarManager() { return actionbarManager; }

    //The tablist manager of the server
    public TablistManager getTablistManager() { return tablistManager; }

    //The master configuration of the plugin
    public Config getMasterConfig() { return masterConfig; }

    //The permission system configuration
    public Config getPermissionConfig() { return permissionConfig; }

    //The world system configuration
    public Config getWorldConfig() { return worldConfig; }

}
