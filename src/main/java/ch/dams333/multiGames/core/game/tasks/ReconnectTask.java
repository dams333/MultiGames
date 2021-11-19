package ch.dams333.multiGames.core.game.tasks;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import ch.dams333.multiGames.MultiGames;

public class ReconnectTask extends BukkitRunnable{

    private MultiGames main;
    private int time;
    private UUID playerID;
    private Location location;
    private PlayerInventory inv;
    

    public UUID getPlayerID() {
        return this.playerID;
    }


    public boolean isPlayer(Player p){
        return this.playerID.equals(p.getUniqueId());
    }

    public ReconnectTask(MultiGames main, UUID playerID, Location location, PlayerInventory inv) {
        this.main = main;
        this.playerID = playerID;
        this.location = location;
        this.inv = inv;
        this.time = main.gameVariablesManager.getVariable("reconnectionTime").getIntValue();
    }

    @Override
    public void run() {
        if(time == 0){
            main.gameManager.killByID(playerID, location, inv, "est mort à cause de sa déconnexion", null);
        }
        time--;    
    }


}
