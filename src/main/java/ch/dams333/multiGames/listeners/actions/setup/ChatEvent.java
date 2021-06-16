package ch.dams333.multiGames.listeners.actions.setup;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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
                        main.gameVariablesManager.isChangingName = null;
                    }
                });
            }
        }else if(main.gameVariablesManager.isCreatingConfig != null){
            if(main.gameVariablesManager.isCreatingConfig == p){
                e.setCancelled(true);
                if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR){
                    main.getServer().getScheduler().runTask(main, new Runnable() {
                        @Override
                        public void run() {
                            MultiGames.INSTANCE.gameVariablesManager.createConfig(p, p.getInventory().getItemInMainHand().getType(), e.getMessage());
                            p.getInventory().remove(p.getInventory().getItemInMainHand());
                            main.gameVariablesManager.isCreatingConfig = null;
                        }
                    });
                }else{
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Veuillez mettre un item dans votre main et répéter l'opération");
                }
            }
        }
    }

}
