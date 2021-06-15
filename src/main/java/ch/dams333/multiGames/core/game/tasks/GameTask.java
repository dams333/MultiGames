package ch.dams333.multiGames.core.game.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.events.GameUpdateTimeEvent;

public class GameTask extends BukkitRunnable{

    private MultiGames main;

    private int time;
    private int episode;

    public GameTask(MultiGames main) {
        this.main = main;
        time = 0;
        episode = 1;
    }    

    @Override
    public void run() {
        time++;

        if(time%1200 == 0){
            episode++;
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Début de l'épisode " + episode);
        }

        if(time == main.gameVariablesManager.getVariable("invicibilityTime").getIntValue()){
            Bukkit.broadcastMessage(ChatColor.GOLD + "Fin de l'invincibilité");
        }
        if(time == main.gameVariablesManager.getVariable("pvpTime").getIntValue()){
            Bukkit.broadcastMessage(ChatColor.GOLD + "Activation du PVP");
        }

        Bukkit.getServer().getPluginManager().callEvent(new GameUpdateTimeEvent(time));
    }


    public int getEpisode() {
        return episode;
    }
    

    public int getTime() {
        return this.time;
    }

}
