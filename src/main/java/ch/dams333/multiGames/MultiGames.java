package ch.dams333.multiGames;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import ch.dams333.multiGames.commands.ValidCommand;
import ch.dams333.multiGames.core.game.GameManager;
import ch.dams333.multiGames.core.teams.TeamsManager;
import ch.dams333.multiGames.listeners.actions.game.ChangeDimension;
import ch.dams333.multiGames.listeners.actions.game.ItemInteract;
import ch.dams333.multiGames.listeners.actions.setup.ChatEvent;
import ch.dams333.multiGames.listeners.actions.setup.SetupItemInteract;
import ch.dams333.multiGames.listeners.connection.joinServer;
import ch.dams333.multiGames.listeners.damage.PlayerDamage;
import ch.dams333.multiGames.listeners.damage.PlayerDamageByEntity;
import ch.dams333.multiGames.listeners.drop.AppleDrop;
import ch.dams333.multiGames.listeners.drop.FlintDrop;
import ch.dams333.multiGames.utils.scoreboard.ScoreboardModifier;
import ch.dams333.multiGames.utils.state.GameStateManager;
import ch.dams333.multiGames.utils.variables.GameVariablesManager;
import ch.dams333.multiGames.utils.world.WorldGenerator;

public class MultiGames extends JavaPlugin{
 
    public GameStateManager gameStateManager;
    public GameVariablesManager gameVariablesManager;
    public GameManager gameManager;
    public TeamsManager teamsManager;

    public Map<Player, WorldGenerator> worldGenerators;
    public Map<Player, ScoreboardModifier> scoreboardModifiers;

    public static MultiGames INSTANCE;

    @Override
    public void onEnable(){

        INSTANCE = this;

        worldGenerators = new HashMap<>();
        scoreboardModifiers = new HashMap<>();

        gameStateManager = new GameStateManager();
        gameVariablesManager = new GameVariablesManager();
        gameManager = new GameManager(this);
        teamsManager = new TeamsManager(this);

        getServer().getPluginManager().registerEvents(new joinServer(this), this);
        getServer().getPluginManager().registerEvents(new SetupItemInteract(this), this);
        getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDamage(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageByEntity(this), this);
        getServer().getPluginManager().registerEvents(new ItemInteract(this), this);
        getServer().getPluginManager().registerEvents(new ChangeDimension(this), this);
        getServer().getPluginManager().registerEvents(new AppleDrop(this), this);
        getServer().getPluginManager().registerEvents(new FlintDrop(this), this);

        getCommand("valid").setExecutor(new ValidCommand(this));
    }

}
