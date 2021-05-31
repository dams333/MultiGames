package ch.dams333.multiGames.listeners.connection;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.items.ItemCreator;
import ch.dams333.multiGames.utils.state.GameState;
import net.md_5.bungee.api.ChatColor;

public class joinServer implements Listener{
    
    private MultiGames main;

    public joinServer(MultiGames main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(main.gameStateManager.isState(GameState.SETUP)){
            //Game setupping
            p.getInventory().clear();
            if(p.hasPermission("multigames.admin")){
                p.setGameMode(GameMode.CREATIVE);
                p.getInventory().setItem(4, ItemCreator.create(Material.CHEST, ChatColor.GOLD + "Gérer la partie"));
            }else{
                p.setGameMode(GameMode.ADVENTURE);
            }
        }
    }

}
