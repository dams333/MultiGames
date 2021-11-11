package ch.dams333.multiGames.core.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.core.game.border.BorderManager;
import ch.dams333.multiGames.core.game.tasks.GameTask;
import ch.dams333.multiGames.core.game.tasks.StartingTask;
import ch.dams333.multiGames.core.scoreboard.ScoreboardManager;
import ch.dams333.multiGames.core.teams.Team;
import ch.dams333.multiGames.utils.events.GameStartEvent;
import ch.dams333.multiGames.utils.show.TitleUtils;
import ch.dams333.multiGames.utils.state.GameState;

public class GameManager {

    private MultiGames main;

    private BorderManager borderManager;
    private ScoreboardManager scoreboardManager;
    private World gameWorld;
    private List<Player> inGamePlayers;
    private int starterPlayerCount;

    public int getStarterPlayerCount() {
        return this.starterPlayerCount;
    }

    public GameManager(MultiGames main) {
        this.main = main;
        borderManager = new BorderManager(main);
        main.getServer().getPluginManager().registerEvents(borderManager, main);
        scoreboardManager = new ScoreboardManager(main);
        inGamePlayers = new ArrayList<>();
    }

    private StartingTask startingTask;
    private GameTask gameTask;


    public GameTask getGameTask() {
        return this.gameTask;
    }


    public List<Player> getInGamePlayers() {
        return this.inGamePlayers;
    }

    public BorderManager getBorderManager() {
        return this.borderManager;
    }

    public ScoreboardManager getScoreboardManager() {
        return this.scoreboardManager;
    }

    public World getGameWorld() {
        return this.gameWorld;
    }    

    public void start() {
        main.gameStateManager.setState(GameState.STARTING);
        StartingTask task = new StartingTask(main, 10, false);
        this.startingTask = task;
        task.runTaskTimer(main, 20, 20);
    }

    public void debugStart()
    {
        main.gameStateManager.setState(GameState.STARTING);
        StartingTask task = new StartingTask(main, 1, true);
        this.startingTask = task;
        task.runTaskTimer(main, 1, 1);    
    }

    public void cancelStart() {
        Bukkit.broadcastMessage(ChatColor.RED + "Annulation du démarrage");
        main.gameStateManager.setState(GameState.SETUP);
        if(startingTask != null){
            startingTask.cancel();
            this.startingTask = null;
        }
    }

    public void startTeleportation(boolean debug) {
        if(this.startingTask != null){
            startingTask.cancel();
            this.startingTask = null;
        }

        if(main.gameVariablesManager.getVariable("randomiseTeams").getBooleanValue()){
            main.teamsManager.randomizeTeams();
        }

        main.gameStateManager.setState(GameState.TELEPORTATION);
        Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Démarrage de la téléportation...");

        starterPlayerCount = Bukkit.getOnlinePlayers().size();

        for(Player p : Bukkit.getOnlinePlayers()){
            inGamePlayers.add(p);
            gameWorld = p.getWorld();
            p.getInventory().clear();
            p.setGameMode(GameMode.SURVIVAL);
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 255));
            p.setHealth(20);
            p.setFoodLevel(20);
        }

        borderManager.setupBorder();
        scoreboardManager.setupScoreboard();

        if(!main.teamsManager.activatedTeams()){
            for(Player p : Bukkit.getOnlinePlayers()){
                spreadPlayer(p);
            }
        }else{
            for(Team team : main.teamsManager.getTeams()){
                if(team.getPlayers().size() > 0){
                    TitleUtils.sendAllActionBar(ChatColor.GRAY + "Téléportation de l'équipe " + team.getChatColor() + team.getTag(), 20);
                    Location spawn = new Location(gameWorld, randomInBorderRange(), 255, randomInBorderRange());
                    spawn = transformSpawn(spawn);
                    spawn.add(0, 1, 0);
                    for(Player p : team.getPlayers()){
                        p.teleport(spawn);
                    }
                }
            }
        }

        if(main.gameVariablesManager.getVariable("activateRegen").getBooleanValue()){
            gameWorld.setGameRuleValue("naturalRegeneration", "true");
            Bukkit.getWorld("world_nether").setGameRuleValue("naturalRegeneration", "true");
            Bukkit.getWorld("world_the_end").setGameRuleValue("naturalRegeneration", "true");
        }else{
            gameWorld.setGameRuleValue("naturalRegeneration", "false");
            Bukkit.getWorld("world_nether").setGameRuleValue("naturalRegeneration", "false");
            Bukkit.getWorld("world_the_end").setGameRuleValue("naturalRegeneration", "false");
        }

        Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Téléportation terminée, démarrage de la partie dans 5 secondes...");
        main.getServer().getScheduler().runTaskLater(main, new Runnable() {
            @Override
            public void run() {
                finishStarting(debug);
            }
        }, 100);
    }

    private void finishStarting(boolean debug) {
        for(Player p : Bukkit.getOnlinePlayers()){
            if(main.gameVariablesManager.getVariable("delayPVP").getBooleanValue()){
                p.getAttribute(org.bukkit.attribute.Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4.0D);
            }else{
                p.getAttribute(org.bukkit.attribute.Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16.0D);
            }
            for(PotionEffect effect : p.getActivePotionEffects()){
                p.removePotionEffect(effect.getType());
            }
        }
        Bukkit.getServer().getPluginManager().callEvent(new GameStartEvent());
        main.gameStateManager.setState(GameState.GAME);
        GameTask task = new GameTask(main);
        this.gameTask = task;
        if(!debug)
            task.runTaskTimer(main, 20, 20);
        else
            task.runTaskTimer(main, 1, 1);
    }

    private void spreadPlayer(Player p){
        TitleUtils.sendAllActionBar(ChatColor.GRAY + "Téléportation de " + p.getName(), 20);
        Location spawn = new Location(gameWorld, randomInBorderRange(), 255, randomInBorderRange());
        spawn = transformSpawn(spawn);
        spawn.add(0, 1, 0);
        p.teleport(spawn);
    }
    private Location transformSpawn(Location loc){
        while (loc.getBlock().getType() == Material.AIR){
            loc.add(0, -1, 0);
        }
        if(loc.getBlock().getType() == Material.WATER){
            loc = new Location(gameWorld, randomInBorderRange(), 255, randomInBorderRange());
            loc = transformSpawn(loc);
        }
        return loc;
    }
    private int randomInBorderRange(){
        int max = MultiGames.INSTANCE.gameVariablesManager.getVariable("startBorderSize").getIntValue() - 100;
        int min = 300;
        if(min >= max) min = (max - 50);
        int coord = random(min, max);
        if(random(0, 1) == 0){
            coord = -1*coord;
        }
        return coord;
    }
    private int random(int min, int max){
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    public int getEpisode() {
        if(gameTask != null){
            return this.gameTask.getEpisode();
        }else{
            return 1;
        }
    }

    public boolean isAlive(Player p) {
        return this.inGamePlayers.contains(p);
    }
    
}
