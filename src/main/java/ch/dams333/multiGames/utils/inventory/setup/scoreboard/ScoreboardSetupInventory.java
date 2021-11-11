package ch.dams333.multiGames.utils.inventory.setup.scoreboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.BaseSetupInventory;
import ch.dams333.multiGames.utils.scoreboard.ScoreboardModifier;

public class ScoreboardSetupInventory {
    public static void open(Player p){

        MultiGames.INSTANCE.scoreboardModifiers.put(p, new ScoreboardModifier());

        ContentManager contentManager = new ContentManager();

        Map<Integer, String> scoreboard = new HashMap<>();
        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("scoreboard").getValue() instanceof HashMap<?, ?>){
            HashMap<?, ?> map = (HashMap<?, ?>) MultiGames.INSTANCE.gameVariablesManager.getVariable("scoreboard").getValue();
            for(Object index : map.keySet()){
                if(index instanceof Integer){
                    scoreboard.put((Integer) index, ChatColor.GRAY + (String) map.get(index));
                }
            }
        }
       
        List<String> viewScoreboard = new ArrayList<>();

        for(int i = 0; i < 16; i++){
            if(!scoreboard.containsKey(i)){
                boolean isNext = false;
                for(int j : scoreboard.keySet()){
                    if(j > i){
                        if(scoreboard.get(j) != null) isNext = true;
                        break;
                    }
                }
                if(isNext) viewScoreboard.add("");
            }else{
                viewScoreboard.add(scoreboard.get(i));
            }
        }

        contentManager.setDefaultItem(0, new InventoryItemStack(Material.BOOKSHELF, ChatColor.GOLD + "Scoreboard actuel:", viewScoreboard));

        contentManager.setDefaultItem(3, new InventoryItemStack(Material.REDSTONE, ChatColor.GOLD + "Monter d'une ligne").setInteractionMethod((player, action) -> {
            ScoreboardModifier modifier = MultiGames.INSTANCE.scoreboardModifiers.get(p);
            modifier.currentLine--;
            if(modifier.currentLine < 1) modifier.currentLine = 1;
            MultiGames.INSTANCE.scoreboardModifiers.put(p, modifier);

            contentManager.changeItemName(4, 0, ChatColor.GOLD + "Ligne " + modifier.currentLine);
            contentManager.changeItemQuantity(4, 0, modifier.currentLine);

            Map<Integer, String> scoreboardNew = new HashMap<>();
            if(MultiGames.INSTANCE.gameVariablesManager.getVariable("scoreboard").getValue() instanceof HashMap<?, ?>){
                HashMap<?, ?> mapNew = (HashMap<?, ?>) MultiGames.INSTANCE.gameVariablesManager.getVariable("scoreboard").getValue();
                for(Object index : mapNew.keySet()){
                    if(index instanceof Integer){
                        scoreboardNew.put((Integer) index, ChatColor.GRAY + (String) mapNew.get(index));
                    }
                }
            }

            String line = "";
            if(scoreboardNew.containsKey(modifier.currentLine - 1)){
                line = scoreboardNew.get(modifier.currentLine - 1);
            }

            ItemMeta m = contentManager.getCurrentItem(4).getItemStack().getItemMeta();
            m.setLore(Arrays.asList(line, "", ChatColor.GREEN + "Clique droit pour modfieir"));
            contentManager.changeItemMeta(4, 0, m);

            contentManager.getInventory().update(p);
        }));

        String line1 = "";
        if(scoreboard.containsKey(0)){
            line1 = scoreboard.get(0);
        }
        contentManager.setDefaultItem(4, new InventoryItemStack(Material.PAPER, ChatColor.GOLD + "Ligne 1", Arrays.asList(line1, "", ChatColor.GREEN + "Clique droit pour modfieir")).setInteractionMethod((player, action) -> {
            ScoreboardModifier modifier = MultiGames.INSTANCE.scoreboardModifiers.get(p);
            modifier.isWriting = true;
            MultiGames.INSTANCE.scoreboardModifiers.put(p, modifier);
            p.closeInventory();
            p.sendMessage(ChatColor.LIGHT_PURPLE + "Ecrivez la ligne " + modifier.currentLine + " dans le chat:");
        }));

        contentManager.setDefaultItem(5, new InventoryItemStack(Material.EMERALD, ChatColor.GOLD + "Descendre d'une ligne").setInteractionMethod((player, action) -> {
            ScoreboardModifier modifier = MultiGames.INSTANCE.scoreboardModifiers.get(p);
            modifier.currentLine++;
            if(modifier.currentLine > 16) modifier.currentLine = 16;
            MultiGames.INSTANCE.scoreboardModifiers.put(p, modifier);

            contentManager.changeItemName(4, 0, ChatColor.GOLD + "Ligne " + modifier.currentLine);
            contentManager.changeItemQuantity(4, 0, modifier.currentLine);

            Map<Integer, String> scoreboardNew = new HashMap<>();
            if(MultiGames.INSTANCE.gameVariablesManager.getVariable("scoreboard").getValue() instanceof HashMap<?, ?>){
                HashMap<?, ?> mapNew = (HashMap<?, ?>) MultiGames.INSTANCE.gameVariablesManager.getVariable("scoreboard").getValue();
                for(Object index : mapNew.keySet()){
                    if(index instanceof Integer){
                        scoreboardNew.put((Integer) index, ChatColor.GRAY + (String) mapNew.get(index));
                    }
                }
            }

            String line = "";
            if(scoreboardNew.containsKey(modifier.currentLine - 1)){
                line = scoreboardNew.get(modifier.currentLine - 1);
            }

            ItemMeta m = contentManager.getCurrentItem(4).getItemStack().getItemMeta();
            m.setLore(Arrays.asList(line, "", ChatColor.GREEN + "Clique droit pour modfieir"));
            contentManager.changeItemMeta(4, 0, m);

            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            BaseSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("ScoreboardSetup")
                                .title(ChatColor.GOLD + "Gérer le scoreboard")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);
    }
}
