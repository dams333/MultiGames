package ch.dams333.multiGames.utils.inventory.setup.items;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;
import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.items.ItemCreator;

public class EnchantLimitSetupInventory {

    public static void open(Player p){
        ContentManager contentManager = new ContentManager();

        int ironProtecLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("ironProtectionLimit").getIntValue();
        int diamondProtecLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("diamondProtectionLimit").getIntValue();
        int ironSharpnessLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("ironSharpnessLimit").getIntValue();
        int diamondSharpnessLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("diamondSharpnessLimit").getIntValue();

        List<String> lore = Arrays.asList(ChatColor.GREEN + "Clique gauche pour augmenter", ChatColor.RED + "Clique droit pour réduire");

        contentManager.setDefaultItem(0, new InventoryItemStack(Material.IRON_CHESTPLATE, ironProtecLimit, ChatColor.GOLD + "Limite de protection sur le fer: " + ironProtecLimit, lore).setInteractionMethod((player, action) -> {
            if(action == InventoryAction.PICKUP_ALL){
                //+
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("ironProtectionLimit").getIntValue() + 1;
                if(newLimit > 4){
                    newLimit = 4;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("ironProtectionLimit", newLimit);
                contentManager.changeItemName(0, 0, ChatColor.GOLD + "Limite de protection sur le fer: " + newLimit);
                contentManager.changeItemQuantity(0, 0, newLimit);
                contentManager.getInventory().update(p);
            }
            if(action == InventoryAction.PICKUP_HALF){
                //-
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("ironProtectionLimit").getIntValue() - 1;
                if(newLimit < 1){
                    newLimit = 1;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("ironProtectionLimit", newLimit);
                contentManager.changeItemName(0, 0, ChatColor.GOLD + "Limite de protection sur le fer: " + newLimit);
                contentManager.changeItemQuantity(0, 0, newLimit);
                contentManager.getInventory().update(p);
            }
        }));

        contentManager.setDefaultItem(1, new InventoryItemStack(Material.DIAMOND_CHESTPLATE, diamondProtecLimit, ChatColor.GOLD + "Limite de protection sur le diamant: " + diamondProtecLimit, lore).setInteractionMethod((player, action) -> {
            if(action == InventoryAction.PICKUP_ALL){
                //+
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("diamondProtectionLimit").getIntValue() + 1;
                if(newLimit > 4){
                    newLimit = 4;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("diamondProtectionLimit", newLimit);
                contentManager.changeItemName(1, 0, ChatColor.GOLD + "Limite de protection sur le diamant: " + newLimit);
                contentManager.changeItemQuantity(1, 0, newLimit);
                contentManager.getInventory().update(p);
            }
            if(action == InventoryAction.PICKUP_HALF){
                //-
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("diamondProtectionLimit").getIntValue() - 1;
                if(newLimit < 1){
                    newLimit = 1;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("diamondProtectionLimit", newLimit);
                contentManager.changeItemName(1, 0, ChatColor.GOLD + "Limite de protection sur le diamant: " + newLimit);
                contentManager.changeItemQuantity(1, 0, newLimit);
                contentManager.getInventory().update(p);
            }
        }));

        contentManager.setDefaultItem(3, new InventoryItemStack(Material.IRON_SWORD, ironSharpnessLimit, ChatColor.GOLD + "Limite de sharpness sur le fer: " + ironSharpnessLimit, lore).setInteractionMethod((player, action) -> {
            if(action == InventoryAction.PICKUP_ALL){
                //+
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("ironSharpnessLimit").getIntValue() + 1;
                if(newLimit > 5){
                    newLimit = 5;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("ironSharpnessLimit", newLimit);
                contentManager.changeItemName(3, 0, ChatColor.GOLD + "Limite de sharpness sur le fer: " + newLimit);
                contentManager.changeItemQuantity(3, 0, newLimit);
                contentManager.getInventory().update(p);
            }
            if(action == InventoryAction.PICKUP_HALF){
                //-
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("ironSharpnessLimit").getIntValue() - 1;
                if(newLimit < 1){
                    newLimit = 1;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("ironSharpnessLimit", newLimit);
                contentManager.changeItemName(3, 0, ChatColor.GOLD + "Limite de sharpness sur le fer: " + newLimit);
                contentManager.changeItemQuantity(3, 0, newLimit);
                contentManager.getInventory().update(p);
            }
        }));

        contentManager.setDefaultItem(4, new InventoryItemStack(Material.DIAMOND_SWORD, diamondSharpnessLimit, ChatColor.GOLD + "Limite de sharpness sur le diamant: " + diamondSharpnessLimit, lore).setInteractionMethod((player, action) -> {
            if(action == InventoryAction.PICKUP_ALL){
                //+
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("diamondSharpnessLimit").getIntValue() + 1;
                if(newLimit > 5){
                    newLimit = 5;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("diamondSharpnessLimit", newLimit);
                contentManager.changeItemName(4, 0, ChatColor.GOLD + "Limite de sharpness sur le diamant: " + newLimit);
                contentManager.changeItemQuantity(4, 0, newLimit);
                contentManager.getInventory().update(p);
            }
            if(action == InventoryAction.PICKUP_HALF){
                //-
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("diamondSharpnessLimit").getIntValue() - 1;
                if(newLimit < 1){
                    newLimit = 1;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("diamondSharpnessLimit", newLimit);
                contentManager.changeItemName(4, 0, ChatColor.GOLD + "Limite de sharpness sur le diamant: " + newLimit);
                contentManager.changeItemQuantity(4, 0, newLimit);
                contentManager.getInventory().update(p);
            }
        }));

        contentManager.setItemForStep(6, 0, new InventoryItemStack(Material.FLINT_AND_STEEL, ChatColor.GOLD + "Enchantement de feu: Désactivée", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher")).setInteractionMethod((player, action) -> {
            contentManager.changeState(6, 1);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateFireEnchant", true);
        }));
        ItemStack fireEnchanted = ItemCreator.create(Material.FLINT_AND_STEEL, ChatColor.GOLD + "Enchantement de feu: Activée", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher"));
        ItemMeta m = fireEnchanted.getItemMeta();
        m.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        fireEnchanted.setItemMeta(m);
        contentManager.setItemForStep(6, 1, new InventoryItemStack(fireEnchanted).setInteractionMethod((player, action) -> {
            contentManager.changeState(6, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateFireEnchant", false);
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            ItemsSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("EnchantLimitsSetup")
                                .title(ChatColor.GOLD + "Gérer les limites d'enchantements")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);

        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("activateFireEnchant").getBooleanValue()){
            contentManager.changeState(6, 1);
        }else{
            contentManager.changeState(6, 0);
        }
        contentManager.getInventory().update(p);
    }
}
