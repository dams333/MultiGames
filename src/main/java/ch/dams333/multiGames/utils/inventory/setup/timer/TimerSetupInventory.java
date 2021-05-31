package ch.dams333.multiGames.utils.inventory.setup.timer;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.BaseSetupInventory;
import ch.dams333.multiGames.utils.time.TimeUtils;
import net.md_5.bungee.api.ChatColor;

public class TimerSetupInventory {

    public static void open(Player p){
        
        ContentManager contentManager = new ContentManager();

        contentManager.setDefaultItem(0, new InventoryItemStack(Material.GOLDEN_APPLE, ChatColor.GOLD + "Invincibilité: " + TimeUtils.secondToString(MultiGames.INSTANCE.gameVariablesManager.getVariable("invicibilityTime").getIntValue(), ":", true)).setInteractionMethod((player, action) -> {
            ChangeTimerSetupInventory.open(p, "invicibilityTime", Material.GOLDEN_APPLE, "Invincibilité: %TIME%", 1, 10, 30, 1, 999999);
        }));

        contentManager.setDefaultItem(2, new InventoryItemStack(Material.IRON_SWORD, ChatColor.GOLD + "PVP: " + TimeUtils.secondToString(MultiGames.INSTANCE.gameVariablesManager.getVariable("pvpTime").getIntValue(), ":", true)).setInteractionMethod((player, action) -> {
            ChangeTimerSetupInventory.open(p, "pvpTime", Material.IRON_SWORD, "PVP: %TIME%", 30, 60, 300, 1, 999999);
        }));

        contentManager.setDefaultItem(4, new InventoryItemStack(Material.BARRIER, ChatColor.GOLD + "Démarrage de la bordure: " + TimeUtils.secondToString(MultiGames.INSTANCE.gameVariablesManager.getVariable("startBorderTime").getIntValue(), ":", true)).setInteractionMethod((player, action) -> {
            ChangeTimerSetupInventory.open(p, "startBorderTime", Material.BARRIER, "Démarrage de la bordure: %TIME%", 60, 300, 900, 1, 999999);
        }));

        contentManager.setDefaultItem(6, new InventoryItemStack(Material.BARRIER, ChatColor.GOLD + "Arrêt de la bordure: " + TimeUtils.secondToString(MultiGames.INSTANCE.gameVariablesManager.getVariable("stopBorderTime").getIntValue(), ":", true)).setInteractionMethod((player, action) -> {
            ChangeTimerSetupInventory.open(p, "stopBorderTime", Material.BARRIER, "Arrêt de la bordure: %TIME%", 60, 300, 900, 1, 999999);
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            BaseSetupInventory.open(p);
        }));


        SimpleInventory inv = SimpleInventory.builder()
                                .id("TimerSetup")
                                .title(ChatColor.GOLD + "Gestion des timers")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);
    }
    
}
