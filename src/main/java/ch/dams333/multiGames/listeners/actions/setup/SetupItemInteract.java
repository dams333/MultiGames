package ch.dams333.multiGames.listeners.actions.setup;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.BaseSetupInventory;
import ch.dams333.multiGames.utils.inventory.teams.TeamsSelectorInventory;
import ch.dams333.multiGames.utils.state.GameState;

public class SetupItemInteract implements Listener{

    private MultiGames main;

    public SetupItemInteract(MultiGames main) {
        this.main = main;
    }

    @EventHandler
    public void interact(PlayerInteractEvent e){
        if(main.gameStateManager.isState(GameState.SETUP) || main.gameStateManager.isState(GameState.STARTING)){
            e.setCancelled(true);
            if(e.getItem() != null && e.getItem().getType() != Material.AIR){
                ItemStack it = e.getItem();
                Player p = e.getPlayer();
                if(it.getType() == Material.CHEST && it.hasItemMeta() && it.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Gérer la partie")){
                    BaseSetupInventory.open(p);
                }
                if(it.getType() == Material.BANNER && it.hasItemMeta() && it.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Choisir une équipe")){
                    TeamsSelectorInventory.open(p);
                }
            }
        }
    }
    
}
