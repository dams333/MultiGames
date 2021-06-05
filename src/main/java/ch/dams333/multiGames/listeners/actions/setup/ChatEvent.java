package ch.dams333.multiGames.listeners.actions.setup;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.other.OtherInventorySetup;

public class ChatEvent implements Listener{
 
    private MultiGames main;

    public ChatEvent(MultiGames main) {
        this.main = main;
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        String message = e.getMessage();
        if(main.scoreboardModifiers.containsKey(p)){
            if(main.scoreboardModifiers.get(p).isWriting){

                e.setCancelled(true);

                main.getServer().getScheduler().runTask(main, new Runnable() {
                    @Override
                    public void run() {
                        main.scoreboardModifiers.get(p).writted(p, message);
                    }
                });
            }
        }else if(main.gameVariablesManager.isChangingName != null){
            if(main.gameVariablesManager.isChangingName == p){
                main.gameVariablesManager.setValue("gameName", message);
                e.setCancelled(true);

                main.getServer().getScheduler().runTask(main, new Runnable() {
                    @Override
                    public void run() {
                        OtherInventorySetup.open(p);
                    }
                });
            }
        }
    }

}
