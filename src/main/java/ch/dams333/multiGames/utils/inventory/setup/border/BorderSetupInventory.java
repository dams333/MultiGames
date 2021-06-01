package ch.dams333.multiGames.utils.inventory.setup.border;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.BaseSetupInventory;
import ch.dams333.multiGames.utils.time.TimeUtils;

public class BorderSetupInventory {
    
    public static void open(Player p){
        ContentManager contentManager = new ContentManager();

        contentManager.setDefaultItem(1, new InventoryItemStack(Material.BARRIER, ChatColor.GOLD + "Rayon avant réduction: " + MultiGames.INSTANCE.gameVariablesManager.getVariable("startBorderSize").getIntValue()).setInteractionMethod((player, action) -> {
            ChangeBorderSetupInventory.open(p, "startBorderSize", "Rayon avant la réduction: %SIZE%");
        }));

        contentManager.setDefaultItem(3, new InventoryItemStack(Material.BARRIER, ChatColor.GOLD + "Rayon après réduction: " + MultiGames.INSTANCE.gameVariablesManager.getVariable("stopBorderSize").getIntValue()).setInteractionMethod((player, action) -> {
            ChangeBorderSetupInventory.open(p, "stopBorderSize", "Rayon après la réduction: %SIZE%");
        }));


        int time = MultiGames.INSTANCE.gameVariablesManager.getVariable("stopBorderTime").getIntValue() - MultiGames.INSTANCE.gameVariablesManager.getVariable("startBorderTime").getIntValue();
        int block = MultiGames.INSTANCE.gameVariablesManager.getVariable("startBorderSize").getIntValue() - MultiGames.INSTANCE.gameVariablesManager.getVariable("stopBorderSize").getIntValue();
        double blockPerSec = Math.round(Double.valueOf(block)/time * 100.0)/100.0;

        contentManager.setDefaultItem(5, new InventoryItemStack(Material.WATCH, ChatColor.GOLD + "Durée de la réduction: " + TimeUtils.secondToCompleteString(time, ":", true), Arrays.asList(ChatColor.GRAY + "" + blockPerSec + " blocs par secondes")));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            BaseSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("BorderSetup")
                                .title(ChatColor.GOLD + "Gérer la vordure")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);
    }
}
