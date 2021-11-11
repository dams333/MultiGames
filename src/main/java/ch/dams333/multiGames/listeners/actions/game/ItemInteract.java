package ch.dams333.multiGames.listeners.actions.game;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import ch.dams333.multiGames.MultiGames;

public class ItemInteract implements Listener {

    private MultiGames main;

    public ItemInteract(MultiGames main) {
        this.main = main;
    }

    @EventHandler
    public void interact(PlayerInteractEvent e){
        if(e.getPlayer().getInventory().getItemInMainHand() != null){
            if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.FISHING_ROD){
                if(!main.gameVariablesManager.getVariable("activateRod").getBooleanValue()){
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "La cânne à pêche est désactivée");
                }
            }
        }
    }
    
}
