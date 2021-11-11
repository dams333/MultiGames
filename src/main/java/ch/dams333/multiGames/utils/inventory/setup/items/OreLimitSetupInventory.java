package ch.dams333.multiGames.utils.inventory.setup.items;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;
import ch.dams333.multiGames.MultiGames;

public class OreLimitSetupInventory {

    public static void open(Player p){
        ContentManager contentManager = new ContentManager();

        int ironLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("ironLimit").getIntValue();
        int goldLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("goldLimit").getIntValue();
        int diamondLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("diamondLimit").getIntValue();

        String ironName = String.valueOf(ironLimit);
        int ironQuantity = ironLimit;
        if(ironLimit < 1){
            ironName = "Désactivée";
            ironQuantity = 1;
        }
        if(ironLimit > 64){
            ironQuantity = 64;
        }
        String goldName = String.valueOf(goldLimit);
        int goldQuantity = goldLimit;
        if(goldLimit < 1){
             goldName = "Désactivée";
             goldQuantity = 1;
        }
        if(goldLimit > 64){
            goldQuantity = 64;
        }
        String diamondName = String.valueOf(diamondLimit);
        int diamondQuantity = diamondLimit;
        if(diamondLimit < 1){
            diamondName = "Désactivée";
            diamondQuantity = 1;
        }
        if(diamondLimit > 64){
            diamondQuantity = 64;
        }

        List<String> lore = Arrays.asList(ChatColor.GREEN + "Clique gauche pour augmenter", ChatColor.RED + "Clique droit pour réduire");

        contentManager.setDefaultItem(1, new InventoryItemStack(Material.IRON_INGOT, ironQuantity, ChatColor.GOLD + "Limite de fer: " + ironName, lore).setInteractionMethod((player, action) -> {
            if(action == InventoryAction.PICKUP_ALL){
                //+
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("ironLimit").getIntValue() + 1;
                MultiGames.INSTANCE.gameVariablesManager.setValue("ironLimit", newLimit);
                String newName = String.valueOf(newLimit);
                int newQuantity = newLimit;
                if(newLimit > 64){
                    newQuantity = 64;
                }
                contentManager.changeItemName(1, 0, ChatColor.GOLD + "Limite de fer: " + newName);
                contentManager.changeItemQuantity(1, 0, newQuantity);
                contentManager.getInventory().update(p);
            }
            if(action == InventoryAction.PICKUP_HALF){
                //-
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("ironLimit").getIntValue() - 1;
                if(newLimit < 0){
                    newLimit = 0;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("ironLimit", newLimit);
                String newName = String.valueOf(newLimit);
                int newQuantity = newLimit;
                if(newLimit < 1){
                    newQuantity = 1;
                    newName = "Désactivée";
                }
                contentManager.changeItemName(1, 0, ChatColor.GOLD + "Limite de fer: " + newName);
                contentManager.changeItemQuantity(1, 0, newQuantity);
                contentManager.getInventory().update(p);
            }
        }));

        contentManager.setDefaultItem(3, new InventoryItemStack(Material.GOLD_INGOT, goldQuantity, ChatColor.GOLD + "Limite d'or: " + goldName, lore).setInteractionMethod((player, action) -> {
            if(action == InventoryAction.PICKUP_ALL){
                //+
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("goldLimit").getIntValue() + 1;
                MultiGames.INSTANCE.gameVariablesManager.setValue("goldLimit", newLimit);
                String newName = String.valueOf(newLimit);
                int newQuantity = newLimit;
                if(newLimit > 64){
                    newQuantity = 64;
                }
                contentManager.changeItemName(3, 0, ChatColor.GOLD + "Limite d'or: " + newName);
                contentManager.changeItemQuantity(3, 0, newQuantity);
                contentManager.getInventory().update(p);
            }
            if(action == InventoryAction.PICKUP_HALF){
                //-
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("goldLimit").getIntValue() - 1;
                if(newLimit < 0){
                    newLimit = 0;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("goldLimit", newLimit);
                String newName = String.valueOf(newLimit);
                int newQuantity = newLimit;
                if(newLimit < 1){
                    newQuantity = 1;
                    newName = "Désactivée";
                }
                contentManager.changeItemName(3, 0, ChatColor.GOLD + "Limite d'or: " + newName);
                contentManager.changeItemQuantity(3, 0, newQuantity);
                contentManager.getInventory().update(p);
            }
        }));

        contentManager.setDefaultItem(5, new InventoryItemStack(Material.DIAMOND, diamondQuantity, ChatColor.GOLD + "Limite de diamant: " + diamondName, lore).setInteractionMethod((player, action) -> {
            if(action == InventoryAction.PICKUP_ALL){
                //+
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("diamondLimit").getIntValue() + 1;
                MultiGames.INSTANCE.gameVariablesManager.setValue("diamondLimit", newLimit);
                String newName = String.valueOf(newLimit);
                int newQuantity = newLimit;
                if(newLimit > 64){
                    newQuantity = 64;
                }
                contentManager.changeItemName(5, 0, ChatColor.GOLD + "Limite de diamant: " + newName);
                contentManager.changeItemQuantity(5, 0, newQuantity);
                contentManager.getInventory().update(p);
            }
            if(action == InventoryAction.PICKUP_HALF){
                //-
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("diamondLimit").getIntValue() - 1;
                if(newLimit < 0){
                    newLimit = 0;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("diamondLimit", newLimit);
                String newName = String.valueOf(newLimit);
                int newQuantity = newLimit;
                if(newLimit < 1){
                    newQuantity = 1;
                    newName = "Désactivée";
                }
                contentManager.changeItemName(5, 0, ChatColor.GOLD + "Limite de diamant: " + newName);
                contentManager.changeItemQuantity(5, 0, newQuantity);
                contentManager.getInventory().update(p);
            }
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            ItemsSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("OreLimitsSetup")
                                .title(ChatColor.GOLD + "Gérer les limites d'items")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);
    }
}
