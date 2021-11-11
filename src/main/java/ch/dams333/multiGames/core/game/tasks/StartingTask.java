package ch.dams333.multiGames.core.game.tasks;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.show.TitleUtils;

public class StartingTask extends BukkitRunnable{

    private MultiGames main;
    private int time;
    private boolean debug;

    public StartingTask(MultiGames main, int startTime, boolean debug) {
        this.main = main;
        this.time = startTime;
        this.debug = debug;
    }

    @Override
    public void run() {
        if(time > 0){
            TitleUtils.sendAllTitle(ChatColor.RED + "Démarrage de la partie dans " + time, 25);
        }else{
            main.gameManager.startTeleportation(debug);
        }

        time--;
    }
    
}
