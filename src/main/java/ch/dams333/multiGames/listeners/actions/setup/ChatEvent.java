package ch.dams333.multiGames.listeners.actions.setup;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.other.OtherInventorySetup;
import ch.dams333.multiGames.utils.state.GameState;

public class ChatEvent implements Listener{
 
    private MultiGames main;

    public ChatEvent(MultiGames main) {
        this.main = main;
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e){
        e.setCancelled(true);
        Player p = e.getPlayer();
        String message = e.getMessage();
        if(main.gameStateManager.isState(GameState.SETUP)){
            if(main.scoreboardModifiers.containsKey(p)){
                if(main.scoreboardModifiers.get(p).isWriting){
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
            }else{
                Bukkit.broadcastMessage(ChatColor.GRAY + p.getDisplayName() + ChatColor.GOLD + " > " + ChatColor.GRAY + e.getMessage());
            }
        }else{
            if(main.teamsManager.activatedTeams()){
                if(e.getMessage().startsWith("!")){
                    String allMessage = e.getMessage().replaceFirst("!", "");
                    if(main.gameVariablesManager.getVariable("activateGlobalChat").getBooleanValue()){
                        if(main.gameVariablesManager.getVariable("activateAnonymousGlobalChat").getBooleanValue()){
                            String name = ChatColor.MAGIC + "";
                            for(int i = 0; i < random(3, 9); i++){
                                name = name + "a";
                            }
                            Bukkit.broadcastMessage(ChatColor.GRAY + name + ChatColor.GOLD + " > " + ChatColor.GRAY + allMessage);
                        }else{
                            Bukkit.broadcastMessage(ChatColor.GRAY + p.getDisplayName() + ChatColor.GOLD + " > " + ChatColor.GRAY + allMessage);
                        }
                    }
                }else{
                    if(main.gameVariablesManager.getVariable("activateTeamChat").getBooleanValue()){
                        if(main.gameVariablesManager.getVariable("activateAnonymousTeamChat").getBooleanValue()){
                            String name = ChatColor.MAGIC + "";
                            for(int i = 0; i < random(3, 9); i++){
                                name = name + "a";
                            }
                            for(Player pl : main.teamsManager.getTeam(p).getPlayers())
                                pl.sendMessage(ChatColor.ITALIC + "[Team] " + "" + ChatColor.RESET + ChatColor.GRAY + name + ChatColor.GOLD + " > " + ChatColor.GRAY + e.getMessage());
                        }else{
                            for(Player pl : main.teamsManager.getTeam(p).getPlayers())
                                pl.sendMessage(ChatColor.ITALIC + "[Team] " + "" + ChatColor.RESET + ChatColor.GRAY + p.getDisplayName() + ChatColor.GOLD + " > " + ChatColor.GRAY + e. getMessage());
                        }
                    }
                }
            }else{
                if(main.gameVariablesManager.getVariable("activateGlobalChat").getBooleanValue()){
                    if(main.gameVariablesManager.getVariable("activateAnonymousGlobalChat").getBooleanValue()){
                        String name = ChatColor.MAGIC + "";
                        for(int i = 0; i < random(3, 9); i++){
                            name = name + "a";
                        }
                        Bukkit.broadcastMessage(ChatColor.GRAY + name + ChatColor.GOLD + " > " + ChatColor.GRAY + e.getMessage());
                    }else{
                        Bukkit.broadcastMessage(ChatColor.GRAY + p.getDisplayName() + ChatColor.GOLD + " > " + ChatColor.GRAY + e.getMessage());
                    }
                }
            }
        }
    }

    private int random(int min, int max){
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

}
