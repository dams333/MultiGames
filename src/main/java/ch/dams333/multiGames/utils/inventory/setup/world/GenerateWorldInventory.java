package ch.dams333.multiGames.utils.inventory.setup.world;

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
import ch.dams333.multiGames.utils.items.ItemCreator;
import ch.dams333.multiGames.utils.world.WorldGenerator;

public class GenerateWorldInventory {

    public static void open(Player p){
        ContentManager contentManager = new ContentManager();

        MultiGames.INSTANCE.worldGenerators.put(p, new WorldGenerator());

        contentManager.setItemForStep(0, 0, new InventoryItemStack(Material.WATER_BUCKET, ChatColor.GOLD + "Générer des océeans: Désactivé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour sélectionner")).setInteractionMethod((player, action) -> {
            contentManager.changeState(0, 1);
            WorldGenerator generator = MultiGames.INSTANCE.worldGenerators.get(p);
            generator.setGenerateOceans(true);
            MultiGames.INSTANCE.worldGenerators.put(p, generator);
            contentManager.getInventory().update(p);
        }));
        ItemStack oceanEnchanted = ItemCreator.create(Material.WATER_BUCKET, ChatColor.GOLD + "Générer des océeans: Activé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour sélectionner"));
        ItemMeta m = oceanEnchanted.getItemMeta();
        m.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        oceanEnchanted.setItemMeta(m);
        contentManager.setItemForStep(0, 1, new InventoryItemStack(oceanEnchanted).setInteractionMethod((player, action) -> {
            contentManager.changeState(0, 0);
            WorldGenerator generator = MultiGames.INSTANCE.worldGenerators.get(p);
            generator.setGenerateOceans(false);
            MultiGames.INSTANCE.worldGenerators.put(p, generator);
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            WorldSetupInventory.open(p);
            if(MultiGames.INSTANCE.worldGenerators.keySet().contains(p)){
                MultiGames.INSTANCE.worldGenerators.remove(p);
            }
        }));

        contentManager.setItemForStep(2, 0, new InventoryItemStack(Material.SAND, ChatColor.GOLD + "Générer des Déserts: Désactivé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour sélectionner")).setInteractionMethod((player, action) -> {
            contentManager.changeState(2, 1);
            WorldGenerator generator = MultiGames.INSTANCE.worldGenerators.get(p);
            generator.setGenerateDeserts(true);
            MultiGames.INSTANCE.worldGenerators.put(p, generator);
            contentManager.getInventory().update(p);
        }));
        ItemStack desertEnchanted = ItemCreator.create(Material.SAND, ChatColor.GOLD + "Générer des Déserts: Activé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour sélectionner"));
        ItemMeta m2 = desertEnchanted.getItemMeta();
        m2.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        desertEnchanted.setItemMeta(m2);
        contentManager.setItemForStep(2, 1, new InventoryItemStack(desertEnchanted).setInteractionMethod((player, action) -> {
            contentManager.changeState(2, 0);
            WorldGenerator generator = MultiGames.INSTANCE.worldGenerators.get(p);
            generator.setGenerateDeserts(false);
            MultiGames.INSTANCE.worldGenerators.put(p, generator);
            contentManager.getInventory().update(p);
        }));

        contentManager.setItemForStep(4, 0, new InventoryItemStack(Material.BARRIER, ChatColor.GOLD + "Générer un spawn: Désactivé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour sélectionner")).setInteractionMethod((player, action) -> {
            contentManager.changeState(4, 1);
            WorldGenerator generator = MultiGames.INSTANCE.worldGenerators.get(p);
            generator.setGenerateSpawn(true);
            MultiGames.INSTANCE.worldGenerators.put(p, generator);
            contentManager.getInventory().update(p);
        }));
        ItemStack spawnEnchanted = ItemCreator.create(Material.BARRIER, ChatColor.GOLD + "Générer un spawns: Activé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour sélectionner"));
        ItemMeta m3 = spawnEnchanted.getItemMeta();
        m3.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        spawnEnchanted.setItemMeta(m3);
        contentManager.setItemForStep(4, 1, new InventoryItemStack(spawnEnchanted).setInteractionMethod((player, action) -> {
            contentManager.changeState(4, 0);
            WorldGenerator generator = MultiGames.INSTANCE.worldGenerators.get(p);
            generator.setGenerateSpawn(false);
            MultiGames.INSTANCE.worldGenerators.put(p, generator);
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(6, new InventoryItemStack(Material.EMERALD_BLOCK, ChatColor.GOLD + "Générer la map").setInteractionMethod((player, action) -> {
            p.closeInventory();
            MultiGames.INSTANCE.worldGenerators.get(p).generateWorld(p);
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            WorldSetupInventory.open(p);
            if(MultiGames.INSTANCE.worldGenerators.keySet().contains(p)){
                MultiGames.INSTANCE.worldGenerators.remove(p);
            }
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("worldSetup")
                                .title(ChatColor.GOLD + "Générer une map")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);

        contentManager.changeState(4, 1);
        inv.update(p);
    }
    
}
