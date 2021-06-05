package ch.dams333.multiGames.utils.inventory.setup.chat;

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

public class ChatSetupInventory {
    public static void open(Player p ){
        ContentManager contentManager = new ContentManager();

        contentManager.setItemForStep(1, 0, new InventoryItemStack(Material.BOOK, ChatColor.GOLD + "Chat général: Désactivé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher")).setInteractionMethod((player, action) -> {
            contentManager.changeState(1, 1);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateGlobalChat", true);
        }));
        ItemStack i1 = ItemCreator.create(Material.BOOK, ChatColor.GOLD + "Chat général: Activé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher"));
        ItemMeta m4 = i1.getItemMeta();
        m4.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m4.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        i1.setItemMeta(m4);
        contentManager.setItemForStep(1, 1, new InventoryItemStack(i1).setInteractionMethod((player, action) -> {
            contentManager.changeState(1, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateGlobalChat", false);
        }));

        contentManager.setItemForStep(2, 0, new InventoryItemStack(Material.PUMPKIN, ChatColor.GOLD + "Chat général anonyme: Désactivé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher")).setInteractionMethod((player, action) -> {
            contentManager.changeState(2, 1);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateAnonymousGlobalChat", true);
        }));
        ItemStack i2 = ItemCreator.create(Material.PUMPKIN, ChatColor.GOLD + "Chat général anonyme: Activé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher"));
        ItemMeta m = i2.getItemMeta();
        m.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        i2.setItemMeta(m);
        contentManager.setItemForStep(2, 1, new InventoryItemStack(i2).setInteractionMethod((player, action) -> {
            contentManager.changeState(2, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateAnonymousGlobalChat", false);
        }));


        contentManager.setItemForStep(4, 0, new InventoryItemStack(Material.PAPER, ChatColor.GOLD + "Chat d'équipe: Désactivé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher")).setInteractionMethod((player, action) -> {
            contentManager.changeState(4, 1);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateTeamChat", true);
        }));
        ItemStack i3 = ItemCreator.create(Material.PAPER, ChatColor.GOLD + "Chat d'équipe: Activé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher"));
        ItemMeta m2 = i3.getItemMeta();
        m2.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        i3.setItemMeta(m2);
        contentManager.setItemForStep(4, 1, new InventoryItemStack(i3).setInteractionMethod((player, action) -> {
            contentManager.changeState(4, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateTeamChat", false);
        }));

        contentManager.setItemForStep(5, 0, new InventoryItemStack(Material.PUMPKIN, ChatColor.GOLD + "Chat d'équipe anonyme: Désactivé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher")).setInteractionMethod((player, action) -> {
            contentManager.changeState(5, 1);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateAnonymousTeamChat", true);
        }));
        ItemStack i4 = ItemCreator.create(Material.PUMPKIN, ChatColor.GOLD + "Chat d'équipe anonyme: Activé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher"));
        ItemMeta m3 = i4.getItemMeta();
        m3.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        i4.setItemMeta(m3);
        contentManager.setItemForStep(5, 1, new InventoryItemStack(i4).setInteractionMethod((player, action) -> {
            contentManager.changeState(5, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateAnonymousTeamChat", false);
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            BaseSetupInventory.open(p);
        }));
        SimpleInventory inv = SimpleInventory.builder()
                                .id("ChatSetup")
                                .title(ChatColor.GOLD + "Gérer le chat")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);

        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("activateGlobalChat").getBooleanValue()){
            contentManager.changeState(1, 1);
        }else{
            contentManager.changeState(1, 0);
        }
        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("activateAnonymousGlobalChat").getBooleanValue()){
            contentManager.changeState(2, 1);
        }else{
            contentManager.changeState(2, 0);
        }

        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("activateTeamChat").getBooleanValue()){
            contentManager.changeState(4, 1);
        }else{
            contentManager.changeState(4, 0);
        }
        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("activateAnonymousTeamChat").getBooleanValue()){
            contentManager.changeState(5, 1);
        }else{
            contentManager.changeState(5, 0);
        }
        contentManager.getInventory().update(p);
    }
}
