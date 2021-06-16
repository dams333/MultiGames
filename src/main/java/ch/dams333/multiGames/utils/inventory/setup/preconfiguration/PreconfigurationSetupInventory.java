package ch.dams333.multiGames.utils.inventory.setup.preconfiguration;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.BaseSetupInventory;

public class PreconfigurationSetupInventory {

    public static void open(Player p){

        ContentManager contentManager = new ContentManager();
        
        contentManager.setDefaultItem(1, new InventoryItemStack(Material.EMERALD_BLOCK, ChatColor.GOLD + "Créer une préconfiguration", Arrays.asList(ChatColor.GRAY + "Les infos stockées seront celles actuellement configurées")).setInteractionMethod((player, action) -> {
            if(MultiGames.INSTANCE.scoreboardModifiers.containsKey(p)){
                MultiGames.INSTANCE.scoreboardModifiers.remove(p);
            }

            p.closeInventory();
            p.sendMessage(ChatColor.LIGHT_PURPLE + "Mettez l'item (seul le matériau sera sauvegardé) dans votre main puis tappez le nom de la configuration dans le chat:");
            MultiGames.INSTANCE.gameVariablesManager.isCreatingConfig = p;
        }));

        contentManager.setDefaultItem(3, new InventoryItemStack(Material.REDSTONE_BLOCK, ChatColor.GOLD + "Supprimer une préconfiguration").setInteractionMethod((player, action) -> {
            RemoveConfigurationSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(5, new InventoryItemStack(Material.STAINED_GLASS, ChatColor.GOLD + "Charger une préconfiguration", Arrays.asList(ChatColor.GRAY + "Les infos actuellement définies seront perdues")).setInteractionMethod((player, action) -> {
            LoadConfigurationSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            BaseSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("PreconfigurationSetup")
                                .title(ChatColor.GOLD + "Gérer les préconfigurations")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);
    }
    
}
