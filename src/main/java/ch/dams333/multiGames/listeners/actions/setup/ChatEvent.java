package ch.dams333.multiGames.listeners.actions.setup;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.other.OtherInventorySetup;
import net.md_5.bungee.api.ChatColor;

public class ChatEvent implements Listener{
 
    @EventHandler
    public void chat(AsyncPlayerChatEvent e){
        if(MultiGames.INSTANCE.scoreboardModifiers.containsKey(e.getPlayer())){
            if(MultiGames.INSTANCE.scoreboardModifiers.get(e.getPlayer()).isWriting){
                e.setCancelled(true);
                MultiGames.INSTANCE.scoreboardModifiers.get(e.getPlayer()).writted(e.getPlayer(), e.getMessage());
            }
        }
        if(MultiGames.INSTANCE.gameVariablesManager.isChangingName != null){
            if(MultiGames.INSTANCE.gameVariablesManager.isChangingName == e.getPlayer()){
                e.setCancelled(true);
                MultiGames.INSTANCE.gameVariablesManager.setValue("gameName", e.getMessage());
                OtherInventorySetup.open(e.getPlayer());
            }
        }
    }

}
