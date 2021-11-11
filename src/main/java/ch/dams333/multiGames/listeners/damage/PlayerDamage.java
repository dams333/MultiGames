package ch.dams333.multiGames.listeners.damage;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.state.GameState;

public class PlayerDamage implements Listener{

    private MultiGames main;

    public PlayerDamage(MultiGames main) {
        this.main = main;
    }

    @EventHandler
    public void damage(EntityDamageEvent e){
        if(!main.gameStateManager.isState(GameState.GAME)){
            e.setCancelled(true);
        }else{
            if(e.getEntity() instanceof Player){
                if(main.gameManager.getGameTask().getTime() < main.gameVariablesManager.getVariable("invicibilityTime").getIntValue()){
                    e.setCancelled(true);
                }
            }
        }
    }
    
}
