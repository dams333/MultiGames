package ch.dams333.multiGames.listeners.actions.game;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import ch.dams333.multiGames.MultiGames;

public class ChangeDimension implements Listener {

    private MultiGames main;

    public ChangeDimension(MultiGames main) {
        this.main = main;
    }

    @EventHandler
    public void dimension(PlayerTeleportEvent e){
        if(e.getTo().getWorld().getName().equalsIgnoreCase("world_nether")){
            if(!main.gameVariablesManager.getVariable("activateNether").getBooleanValue()){
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "Le nether est désactivé");
            }
        }
        if(e.getTo().getWorld().getName().equalsIgnoreCase("world_the_end")){
            if(!main.gameVariablesManager.getVariable("activateEnd").getBooleanValue()){
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "L'end est désactivé");
            }
        }
    }
    
}
