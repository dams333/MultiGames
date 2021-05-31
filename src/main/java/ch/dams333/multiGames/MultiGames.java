package ch.dams333.multiGames;

import org.bukkit.plugin.java.JavaPlugin;

import ch.dams333.multiGames.listeners.actions.setup.SetupItemInteract;
import ch.dams333.multiGames.listeners.connection.joinServer;
import ch.dams333.multiGames.utils.state.GameStateManager;
import ch.dams333.multiGames.utils.variables.GameVariablesManager;

public class MultiGames extends JavaPlugin{
 
    public GameStateManager gameStateManager;
    public GameVariablesManager gameVariablesManager;

    public static MultiGames INSTANCE;

    @Override
    public void onEnable(){

        INSTANCE = this;

        gameStateManager = new GameStateManager();
        gameVariablesManager = new GameVariablesManager();

        getServer().getPluginManager().registerEvents(new joinServer(this), this);
        getServer().getPluginManager().registerEvents(new SetupItemInteract(this), this);
    }

}
