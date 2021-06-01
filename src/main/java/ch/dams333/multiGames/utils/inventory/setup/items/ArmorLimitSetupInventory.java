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

public class ArmorLimitSetupInventory {

    public static void open(Player p){
        ContentManager contentManager = new ContentManager();

        int ironArmorLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("ironArmorLimit").getIntValue();
        int diamondArmorLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("diamondArmorLimit").getIntValue();

        List<String> lore = Arrays.asList(ChatColor.GREEN + "Clique gauche pour augmenter", ChatColor.RED + "Clique droit pour réduire");

        contentManager.setDefaultItem(1, new InventoryItemStack(Material.IRON_CHESTPLATE, ironArmorLimit, ChatColor.GOLD + "Limite de pièces en fer: " + ironArmorLimit, lore).setInteractionMethod((player, action) -> {
            if(action == InventoryAction.PICKUP_ALL){
                //+
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("ironArmorLimit").getIntValue() + 1;
                if(newLimit > 4){
                    newLimit = 4;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("ironArmorLimit", newLimit);
                contentManager.changeItemName(1, 0, ChatColor.GOLD + "Limite de pièces en fer: " + newLimit);
                contentManager.changeItemQuantity(1, 0, newLimit);
                contentManager.getInventory().update(p);
            }
            if(action == InventoryAction.PICKUP_HALF){
                //-
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("ironArmorLimit").getIntValue() - 1;
                if(newLimit < 1){
                    newLimit = 1;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("ironArmorLimit", newLimit);
                contentManager.changeItemName(1, 0, ChatColor.GOLD + "Limite de pièces en fer: " + newLimit);
                contentManager.changeItemQuantity(1, 0, newLimit);
                contentManager.getInventory().update(p);
            }
        }));

        contentManager.setDefaultItem(5, new InventoryItemStack(Material.DIAMOND_CHESTPLATE, diamondArmorLimit, ChatColor.GOLD + "Limite de pièces en diamant: " + diamondArmorLimit, lore).setInteractionMethod((player, action) -> {
            if(action == InventoryAction.PICKUP_ALL){
                //+
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("diamondArmorLimit").getIntValue() + 1;
                if(newLimit > 4){
                    newLimit = 4;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("diamondArmorLimit", newLimit);
                contentManager.changeItemName(5, 0, ChatColor.GOLD + "Limite de pièces en diamant: " + newLimit);
                contentManager.changeItemQuantity(5, 0, newLimit);
                contentManager.getInventory().update(p);
            }
            if(action == InventoryAction.PICKUP_HALF){
                //-
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("diamondArmorLimit").getIntValue() - 1;
                if(newLimit < 1){
                    newLimit = 1;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("diamondArmorLimit", newLimit);
                contentManager.changeItemName(5, 0, ChatColor.GOLD + "Limite de pièces en diamant: " + newLimit);
                contentManager.changeItemQuantity(5, 0, newLimit);
                contentManager.getInventory().update(p);
            }
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            ItemsSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("ArmorLimitsSetup")
                                .title(ChatColor.GOLD + "Gérer les limites d'enchantements")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);
    }
}
