package ch.dams333.multiGames.utils.inventory.setup;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.border.BorderSetupInventory;
import ch.dams333.multiGames.utils.inventory.setup.chat.ChatSetupInventory;
import ch.dams333.multiGames.utils.inventory.setup.dimensions.DimensionsSetupInventory;
import ch.dams333.multiGames.utils.inventory.setup.drops.DropsSetupInventory;
import ch.dams333.multiGames.utils.inventory.setup.items.ItemsSetupInventory;
import ch.dams333.multiGames.utils.inventory.setup.other.OtherInventorySetup;
import ch.dams333.multiGames.utils.inventory.setup.pvp.PvpSetupInventory;
import ch.dams333.multiGames.utils.inventory.setup.scoreboard.ScoreboardSetupInventory;
import ch.dams333.multiGames.utils.inventory.setup.teams.TeamsSetupInventory;
import ch.dams333.multiGames.utils.inventory.setup.timer.TimerSetupInventory;
import ch.dams333.multiGames.utils.inventory.setup.view.ViewSetupInventory;
import ch.dams333.multiGames.utils.inventory.setup.world.WorldSetupInventory;
import ch.dams333.multiGames.utils.state.GameState;

public class BaseSetupInventory {
    
    public static void open(Player p){
        ContentManager contentManager = new ContentManager();

        InventoryItemStack glassItem = new InventoryItemStack(Material.STAINED_GLASS_PANE, (byte) 13, 1, " ");
        contentManager.setDefaultItem(0, glassItem);
        contentManager.setDefaultItem(1, glassItem);
        contentManager.setDefaultItem(9, glassItem);

        contentManager.setDefaultItem(7, glassItem);
        contentManager.setDefaultItem(8, glassItem);
        contentManager.setDefaultItem(17, glassItem);

        contentManager.setDefaultItem(36, glassItem);
        contentManager.setDefaultItem(45, glassItem);
        contentManager.setDefaultItem(46, glassItem);

        contentManager.setDefaultItem(44, glassItem);
        contentManager.setDefaultItem(52, glassItem);
        contentManager.setDefaultItem(53, glassItem);

        contentManager.setDefaultItem(2, new InventoryItemStack(Material.BARRIER, ChatColor.GOLD + "Gestion de la bordure").setInteractionMethod((player, action) -> {
            BorderSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(4, new InventoryItemStack(Material.SLIME_BALL, ChatColor.GOLD + "Autres").setInteractionMethod((player, action) -> {
            OtherInventorySetup.open(p);
        }));

        contentManager.setDefaultItem(6, new InventoryItemStack(Material.CHEST, ChatColor.GOLD + "Gestion des items").setInteractionMethod((player, action) -> {
            ItemsSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(10, new InventoryItemStack(Material.IRON_SWORD, ChatColor.GOLD + "Gestion du PVP").setInteractionMethod((player, action) -> {
            PvpSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(16, new InventoryItemStack(Material.ENDER_PORTAL_FRAME, ChatColor.GOLD + "Gestion des dimensions").setInteractionMethod((player, action) -> {
            DimensionsSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(18, new InventoryItemStack(Material.WATCH, ChatColor.GOLD + "Gestion des Timers").setInteractionMethod((player, action) -> {
            TimerSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(26, new InventoryItemStack(Material.BANNER, ChatColor.GOLD + "Gestion des Équipes").setInteractionMethod((player, action) -> {
            TeamsSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(27, new InventoryItemStack(Material.BOOKSHELF, ChatColor.GOLD + "Gestion du Scoreboard").setInteractionMethod((player, action) -> {
            ScoreboardSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(35, new InventoryItemStack(Material.APPLE, ChatColor.GOLD + "Gestion des Drops").setInteractionMethod((player, action) -> {
            DropsSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(43, new InventoryItemStack(Material.GRASS, ChatColor.GOLD + "Gestion de la carte").setInteractionMethod((player, action) -> {
            WorldSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(22, new InventoryItemStack(Material.ENDER_CHEST, ChatColor.GOLD + "Modes de jeu").setInteractionMethod((player, action) -> {

        }));

        contentManager.setDefaultItem(31, new InventoryItemStack(Material.BOOK_AND_QUILL, ChatColor.GOLD + "Scénarios").setInteractionMethod((player, action) -> {
            
        }));

        contentManager.setDefaultItem(37, new InventoryItemStack(Material.PAPER, ChatColor.GOLD + "Gestion du chat").setInteractionMethod((player, action) -> {
            ChatSetupInventory.open(p);
        }));

        contentManager.setDefaultItem(47, new InventoryItemStack(Material.BEACON, ChatColor.GOLD + "Gestion des affichages").setInteractionMethod((player, action) -> {
            ViewSetupInventory.open(p);
        }));

        if(MultiGames.INSTANCE.gameStateManager.isState(GameState.SETUP)){
            contentManager.setDefaultItem(49, new InventoryItemStack(Material.EMERALD_BLOCK, ChatColor.GOLD + "Démarrage de la partie").setInteractionMethod((player, action) -> {
                p.closeInventory();
                MultiGames.INSTANCE.gameManager.start();
            }));
        }
        if(MultiGames.INSTANCE.gameStateManager.isState(GameState.STARTING)){
            contentManager.setDefaultItem(49, new InventoryItemStack(Material.REDSTONE_BLOCK, ChatColor.GOLD + "Annuler le démarrage").setInteractionMethod((player, action) -> {
                p.closeInventory();
                MultiGames.INSTANCE.gameManager.cancelStart();
            }));
        }

        contentManager.setDefaultItem(51, new InventoryItemStack(Material.STAINED_GLASS, ChatColor.GOLD + "Préconfigurations").setInteractionMethod((player, action) -> {

        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("BaseSetup")
                                .title(ChatColor.GOLD + "Gérer la partie")
                                .rows(6)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);
    }

}
