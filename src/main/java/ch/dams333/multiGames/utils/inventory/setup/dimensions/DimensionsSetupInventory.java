package ch.dams333.multiGames.utils.inventory.setup.dimensions;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.BaseSetupInventory;
import ch.dams333.multiGames.utils.items.ItemCreator;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;

public class DimensionsSetupInventory {

    public static void open(Player p){

        ContentManager contentManager = new ContentManager();

        contentManager.setItemForStep(2, 0, new InventoryItemStack(Material.NETHERRACK, ChatColor.GOLD + "Nether: Désactivé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher")).setInteractionMethod((player, action) -> {
            contentManager.changeState(2, 1);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateNether", true);
        }));
        ItemStack netherEnchanted = ItemCreator.create(Material.NETHERRACK, ChatColor.GOLD + "Nether: Activé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher"));
        ItemMeta m = netherEnchanted.getItemMeta();
        m.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        netherEnchanted.setItemMeta(m);
        contentManager.setItemForStep(2, 1, new InventoryItemStack(netherEnchanted).setInteractionMethod((player, action) -> {
            contentManager.changeState(2, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateNether", false);
        }));


        contentManager.setItemForStep(5, 0, new InventoryItemStack(Material.ENDER_STONE, ChatColor.GOLD + "End: Désactivé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher")).setInteractionMethod((player, action) -> {
            contentManager.changeState(5, 1);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateEnd", true);
        }));
        ItemStack endEnchanted = ItemCreator.create(Material.ENDER_STONE, ChatColor.GOLD + "End: Activé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher"));
        ItemMeta m2 = endEnchanted.getItemMeta();
        m2.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        endEnchanted.setItemMeta(m2);
        contentManager.setItemForStep(5, 1, new InventoryItemStack(endEnchanted).setInteractionMethod((player, action) -> {
            contentManager.changeState(5, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateEnd", false);
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            BaseSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("DimensionsSetup")
                                .title(ChatColor.GOLD + "Gérer les dimensions")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);

        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("activateNether").getBooleanValue()){
            contentManager.changeState(2, 1);
        }else{
            contentManager.changeState(2, 0);
        }
        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("activateEnd").getBooleanValue()){
            contentManager.changeState(5, 1);
        }else{
            contentManager.changeState(5, 0);
        }
        contentManager.getInventory().update(p);
    }
    
}
