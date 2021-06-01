package ch.dams333.multiGames.utils.inventory.setup.items;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.BaseSetupInventory;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;

public class ItemsSetupInventory {
    
    public static void open(Player p){
        ContentManager contentManager = new ContentManager();

        contentManager.setDefaultItem(0, new InventoryItemStack(Material.DIAMOND, ChatColor.GOLD + "Limites de minerais").setInteractionMethod((player, action) -> {
            OreLimitSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(2, new InventoryItemStack(Material.ENCHANTED_BOOK, ChatColor.GOLD + "Limites d'enchantements").setInteractionMethod((player, action) -> {
            EnchantLimitSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(4, new InventoryItemStack(Material.DIAMOND_CHESTPLATE, ChatColor.GOLD + "Limites d'armure").setInteractionMethod((player, action) -> {
            ArmorLimitSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(6, new InventoryItemStack(Material.CHEST, ChatColor.GOLD + "Inventaire de départ").setInteractionMethod((player, action) -> {
            contentManager.getInventory().close(p);
            p.getInventory().clear();
            
            if(MultiGames.INSTANCE.gameVariablesManager.getVariable("startInventory").getValue() instanceof ArrayList<?>){
                ArrayList<?> list = (ArrayList<?>) MultiGames.INSTANCE.gameVariablesManager.getVariable("startInventory").getValue();
                for(Object it : list){
                    if(it instanceof ItemStack){
                        p.getInventory().addItem((ItemStack)it);
                    }
                }
            }

            p.sendMessage(ChatColor.LIGHT_PURPLE + "Tapez /valid quand vous avez finit de préparer l'inventaire");
        }));
        
        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            BaseSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("ItemsSetup")
                                .title(ChatColor.GOLD + "Gérer les items")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);
    }

}
