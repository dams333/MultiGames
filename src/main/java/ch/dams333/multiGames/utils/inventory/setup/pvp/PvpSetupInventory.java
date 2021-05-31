package ch.dams333.multiGames.utils.inventory.setup.pvp;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.BaseSetupInventory;
import ch.dams333.multiGames.utils.items.ItemCreator;

public class PvpSetupInventory {
    
    public static void open(Player p){
        ContentManager contentManager = new ContentManager();

        contentManager.setItemForStep(1, 0, new InventoryItemStack(Material.DIAMOND_SWORD, ChatColor.GOLD + "PVP sans délai", Arrays.asList(ChatColor.GRAY + "Clique gauche pour sélectionner")).setInteractionMethod((player, action) -> {
            contentManager.changeState(1, 1);
            contentManager.changeState(2, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("delayPVP", false);
        }));
        ItemStack noDelayEncanchanted = ItemCreator.create(Material.DIAMOND_SWORD, ChatColor.GOLD + "PVP sans délai", Arrays.asList(ChatColor.GRAY + "Clique gauche pour sélectionner"));
        ItemMeta m = noDelayEncanchanted.getItemMeta();
        m.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        noDelayEncanchanted.setItemMeta(m);
        contentManager.setItemForStep(1, 1, new InventoryItemStack(noDelayEncanchanted));

        contentManager.setItemForStep(2, 0, new InventoryItemStack(Material.IRON_SWORD, ChatColor.GOLD + "PVP avec délai", Arrays.asList(ChatColor.GRAY + "Clique gauche pour sélectionner")).setInteractionMethod((player, action) -> {
            contentManager.changeState(2, 1);
            contentManager.changeState(1, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("delayPVP", true);
        }));
        ItemStack delayEncanchanted = ItemCreator.create(Material.IRON_SWORD, ChatColor.GOLD + "PVP avec délai", Arrays.asList(ChatColor.GRAY + "Clique gauche pour sélectionner"));
        ItemMeta m2 = delayEncanchanted.getItemMeta();
        m2.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        delayEncanchanted.setItemMeta(m2);
        contentManager.setItemForStep(2, 1, new InventoryItemStack(delayEncanchanted));



        contentManager.setItemForStep(6, 0, new InventoryItemStack(Material.FISHING_ROD, ChatColor.GOLD + "Canne à pêche", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher")).setInteractionMethod((player, action) -> {
            contentManager.changeState(6, 1);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateRod", true);
        }));
        ItemStack rodEnchanted = ItemCreator.create(Material.FISHING_ROD, ChatColor.GOLD + "Canne à pêche", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher"));
        ItemMeta m3 = rodEnchanted.getItemMeta();
        m3.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        rodEnchanted.setItemMeta(m3);
        contentManager.setItemForStep(6, 1, new InventoryItemStack(rodEnchanted).setInteractionMethod((player, action) -> {
            contentManager.changeState(6, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateRod", false);
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            BaseSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("PvpSetup")
                                .title(ChatColor.GOLD + "Gérer le PVP")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);

        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("delayPVP").getBooleanValue()){
            contentManager.changeState(2, 1);
            contentManager.changeState(1, 0);
        }else{
            contentManager.changeState(1, 1);
            contentManager.changeState(2, 0);
        }
        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("activateRod").getBooleanValue()){
            contentManager.changeState(6, 1);
        }else{
            contentManager.changeState(6, 0);
        }
        contentManager.getInventory().update(p);
    }

}
