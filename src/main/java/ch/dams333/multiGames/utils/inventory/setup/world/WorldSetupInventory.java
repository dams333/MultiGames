package ch.dams333.multiGames.utils.inventory.setup.world;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.multiGames.utils.inventory.setup.BaseSetupInventory;
import ch.dams333.multiGames.utils.world.WorldPregenerator;

public class WorldSetupInventory {

    public static void open(Player p ){
        ContentManager contentManager = new ContentManager();

        contentManager.setDefaultItem(1, new InventoryItemStack(Material.SEA_LANTERN, ChatColor.GOLD + "Prégénérer la map").setInteractionMethod((player, action) -> {
            p.closeInventory();
            WorldPregenerator.pregenerate(p);
        }));

        contentManager.setDefaultItem(3, new InventoryItemStack(Material.LOG_2, (byte) 1, 1, ChatColor.GOLD + "Générer une Ruft Forest").setInteractionMethod((player, action) -> {
            
        }));

        contentManager.setDefaultItem(5, new InventoryItemStack(Material.GRASS, ChatColor.GOLD + "Générer une nouvelle map").setInteractionMethod((player, action) -> {
            GenerateWorldInventory.open(p);
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            BaseSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("worldSetup")
                                .title(ChatColor.GOLD + "Gérer la map")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);
    }
    
}
