package ch.dams333.multiGames.utils.inventory.setup.preconfiguration;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.BaseSetupInventory;
import ch.dams333.multiGames.utils.variables.GameConfiguration;

public class LoadConfigurationSetupInventory {
    public static void open(Player p){

        ContentManager contentManager = new ContentManager();

        int index = 0;
        for(GameConfiguration gameConfiguration : MultiGames.INSTANCE.gameVariablesManager.getConfigurations()){

            contentManager.setDefaultItem(index, new InventoryItemStack(gameConfiguration.getMaterial(), ChatColor.GOLD + gameConfiguration.getName()).setInteractionMethod((player, action) -> {
                MultiGames.INSTANCE.gameVariablesManager.loadConfiguration(gameConfiguration.getUuid());
                BaseSetupInventory.open(p);
            }));

            index++;
            if(index > 40) break;
        }

        contentManager.setDefaultItem(49, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            PreconfigurationSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("LoadPreconfigurationSetup")
                                .title(ChatColor.GOLD + "Charger une préconfigurations")
                                .rows(6)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);
    }
}
