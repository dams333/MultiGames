package ch.dams333.multiGames.listeners.actions.game;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import ch.dams333.multiGames.MultiGames;

public class BreakOre implements Listener {

    
    private MultiGames main;

    private Map<Player, Integer> ironLimit;
    private Map<Player, Integer> goldLimit;
    private Map<Player, Integer> diamondLimit;

    public BreakOre(MultiGames multiGames) {
        this.main = multiGames;
        this.ironLimit = new HashMap<>();
        this.goldLimit = new HashMap<>();
        this.diamondLimit = new HashMap<>();
    }

    @EventHandler
    public void breakOre(BlockBreakEvent e){
        if(e.getBlock().getType() == Material.IRON_ORE){
            if(!ironLimit.containsKey(e.getPlayer())){
                ironLimit.put(e.getPlayer(), 0);
            }
            if(main.gameVariablesManager.getVariable("ironLimit").getIntValue() != 0){
                if(ironLimit.get(e.getPlayer()) >= main.gameVariablesManager.getVariable("ironLimit").getIntValue()){
                    e.getPlayer().sendMessage(ChatColor.RED + "Vous aved déjà atteint votre limite de fer");
                    e.setCancelled(true);
                }else{
                    ironLimit.put(e.getPlayer(), ironLimit.get(e.getPlayer()) + 1);
                }
            }
        }
        if(e.getBlock().getType() == Material.GOLD_ORE){
            if(!goldLimit.containsKey(e.getPlayer())){
                goldLimit.put(e.getPlayer(), 0);
            }
            if(main.gameVariablesManager.getVariable("goldLimit").getIntValue() != 0){
                if(goldLimit.get(e.getPlayer()) >= main.gameVariablesManager.getVariable("goldLimit").getIntValue()){
                    e.getPlayer().sendMessage(ChatColor.RED + "Vous aved déjà atteint votre limite de or");
                    e.setCancelled(true);
                }else{
                    goldLimit.put(e.getPlayer(), goldLimit.get(e.getPlayer()) + 1);
                }
            }
        }
        if(e.getBlock().getType() == Material.DIAMOND_ORE){
            if(!diamondLimit.containsKey(e.getPlayer())){
                diamondLimit.put(e.getPlayer(), 0);
            }
            if(main.gameVariablesManager.getVariable("diamondLimit").getIntValue() != 0){
                if(diamondLimit.get(e.getPlayer()) >= main.gameVariablesManager.getVariable("diamondLimit").getIntValue()){
                    e.getPlayer().sendMessage(ChatColor.RED + "Vous aved déjà atteint votre limite de diamant");
                    e.setCancelled(true);
                }else{
                    diamondLimit.put(e.getPlayer(), diamondLimit.get(e.getPlayer()) + 1);
                }
            }
        }
    }
    
}
