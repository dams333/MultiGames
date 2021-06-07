package ch.dams333.multiGames.core.game.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.events.GameUpdateTimeEvent;

public class GameTask extends BukkitRunnable{

    private MultiGames main;

    private int time;

    public GameTask(MultiGames main) {
        this.main = main;
        time = 0;
    }


    @Override
    public void run() {
        time++;
        Bukkit.getServer().getPluginManager().callEvent(new GameUpdateTimeEvent(time));
    }
    
}
