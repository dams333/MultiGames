package ch.dams333.multiGames.utils.inventory.setup.view;

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

public class ViewSetupInventory {
    public static void open(Player p ){
        ContentManager contentManager = new ContentManager();

        contentManager.setItemForStep(1, 0, new InventoryItemStack(Material.SKULL_ITEM, (byte) 1, 1, ChatColor.GOLD + "Annoncer la mort: Désactivé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher")).setInteractionMethod((player, action) -> {
            contentManager.changeState(1, 1);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("announceDeath", true);
        }));
        ItemStack death = ItemCreator.create(Material.SKULL_ITEM, ChatColor.GOLD + "Annoncer la mort: Activé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher"));
        contentManager.setItemForStep(1, 1, new InventoryItemStack(death).setInteractionMethod((player, action) -> {
            contentManager.changeState(1, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("announceDeath", false);
        }));

        contentManager.setItemForStep(2, 0, new InventoryItemStack(Material.IRON_SWORD, ChatColor.GOLD + "Annoncer le tueur: Désactivé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher")).setInteractionMethod((player, action) -> {
            contentManager.changeState(2, 1);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("announceKiller", true);
        }));
        ItemStack killer = ItemCreator.create(Material.IRON_SWORD, ChatColor.GOLD + "Annoncer le tueur: Activé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher"));
        ItemMeta m = killer.getItemMeta();
        m.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        killer.setItemMeta(m);
        contentManager.setItemForStep(2, 1, new InventoryItemStack(killer).setInteractionMethod((player, action) -> {
            contentManager.changeState(2, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("announceKiller", false);
        }));


        contentManager.setItemForStep(4, 0, new InventoryItemStack(Material.PAPER, ChatColor.GOLD + "Voir la vie dans le TAB: Désactivé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher")).setInteractionMethod((player, action) -> {
            contentManager.changeState(4, 1);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("viewTabHealth", true);
        }));
        ItemStack tab = ItemCreator.create(Material.PAPER, ChatColor.GOLD + "Voir la vie dans le TAB: Activé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher"));
        ItemMeta m2 = tab.getItemMeta();
        m2.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tab.setItemMeta(m2);
        contentManager.setItemForStep(4, 1, new InventoryItemStack(tab).setInteractionMethod((player, action) -> {
            contentManager.changeState(4, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("viewTabHealth", false);
        }));

        contentManager.setItemForStep(5, 0, new InventoryItemStack(Material.GOLDEN_APPLE, ChatColor.GOLD + "Voir la vie au dessus de la tête: Désactivé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher")).setInteractionMethod((player, action) -> {
            contentManager.changeState(5, 1);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("viewHeadHealth", true);
        }));
        ItemStack head = ItemCreator.create(Material.GOLDEN_APPLE, (byte) 1, ChatColor.GOLD + "Voir la vie au dessus de la tête: Activé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher"));
        ItemMeta m3 = head.getItemMeta();
        m3.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        head.setItemMeta(m3);
        contentManager.setItemForStep(5, 1, new InventoryItemStack(head).setInteractionMethod((player, action) -> {
            contentManager.changeState(5, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("viewHeadHealth", false);
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            BaseSetupInventory.open(p);
        }));
        SimpleInventory inv = SimpleInventory.builder()
                                .id("ViewSetup")
                                .title(ChatColor.GOLD + "Gérer les affichages")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);

        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("announceDeath").getBooleanValue()){
            contentManager.changeState(1, 1);
        }else{
            contentManager.changeState(1, 0);
        }
        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("announceKiller").getBooleanValue()){
            contentManager.changeState(2, 1);
        }else{
            contentManager.changeState(2, 0);
        }

        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("viewTabHealth").getBooleanValue()){
            contentManager.changeState(4, 1);
        }else{
            contentManager.changeState(4, 0);
        }
        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("viewHeadHealth").getBooleanValue()){
            contentManager.changeState(5, 1);
        }else{
            contentManager.changeState(5, 0);
        }
        contentManager.getInventory().update(p);
    }
}
