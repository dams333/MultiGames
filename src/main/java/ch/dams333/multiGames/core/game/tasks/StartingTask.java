package ch.dams333.multiGames.core.game.tasks;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.show.TitleUtils;

public class StartingTask extends BukkitRunnable{

    private MultiGames main;
    private int time;

    public StartingTask(MultiGames main) {
        this.main = main;
        //this.time = 10;
        this.time = 1; //DEBUG
    }

    @Override
    public void run() {
        if(time > 0){
            TitleUtils.sendAllTitle(ChatColor.RED + "Démarrage de la partie dans " + time, 25);
        }else{
            main.gameManager.startTeleportation();
        }

        time--;
    }
    
}
