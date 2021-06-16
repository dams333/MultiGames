package ch.dams333.multiGames.utils.inventory.setup.teams;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.BaseSetupInventory;
import ch.dams333.multiGames.utils.items.ItemCreator;

public class TeamsSetupInventory {
    public static void open(Player p){
        ContentManager contentManager = new ContentManager();

        int teamsNumber = MultiGames.INSTANCE.gameVariablesManager.getVariable("teamsNumber").getIntValue();
        boolean randomTeams = MultiGames.INSTANCE.gameVariablesManager.getVariable("randomiseTeams").getBooleanValue();

        int numerOfItem = teamsNumber;
        if(numerOfItem < 2) numerOfItem = 2;

        
        contentManager.setDefaultItem(2, new InventoryItemStack(Material.BARRIER, 1, ChatColor.GOLD + "Équipes: Désactivées", Arrays.asList(ChatColor.GREEN + "Clique droit pour activer")).setInteractionMethod((player, action) -> {
            if(action == InventoryAction.PICKUP_ALL){
                MultiGames.INSTANCE.gameVariablesManager.setValue("teamsNumber", 2);
                contentManager.changeState(2, 1);
                contentManager.changeItemName(2, 1, ChatColor.GOLD + "Équipes: 2");
                contentManager.getInventory().update(p);
            }
        }));
        contentManager.setItemForStep(2, 1, new InventoryItemStack(Material.BANNER, (byte) 15, numerOfItem, ChatColor.GOLD + "Équipes: " + teamsNumber, Arrays.asList(ChatColor.GREEN + "Clique droit pour augmenter", ChatColor.RED + "Clique droit pour réduire")).setInteractionMethod((player, action) -> {
            int current = MultiGames.INSTANCE.gameVariablesManager.getVariable("teamsNumber").getIntValue();
            if(action == InventoryAction.PICKUP_ALL){
                //+
                current++;
                if(current > 44) current = 44;
                MultiGames.INSTANCE.gameVariablesManager.setValue("teamsNumber", current);
                contentManager.changeItemName(2, 1, ChatColor.GOLD + "Équipes: " + current);
                contentManager.changeItemQuantity(2, 1, current);
            }
            if(action == InventoryAction.PICKUP_HALF){
                //-
                current--;
                if(current > 54) current = 54;
                MultiGames.INSTANCE.gameVariablesManager.setValue("teamsNumber", current);
                contentManager.changeItemName(2, 1, ChatColor.GOLD + "Équipes: " + current);
                contentManager.changeItemQuantity(2, 1, current);
            }
            if(current < 2){
                contentManager.changeState(2, 0);
            }
            contentManager.getInventory().update(p);
        }));


        contentManager.setItemForStep(6, 0, new InventoryItemStack(Material.EYE_OF_ENDER, ChatColor.GOLD + "Choix des teams: Désactivé (aléatoire)", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher")).setInteractionMethod((player, action) -> {
            contentManager.changeState(6, 1);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("randomiseTeams", false);
        }));
        ItemStack choosEnchanted = ItemCreator.create(Material.EYE_OF_ENDER, ChatColor.GOLD + "Choix des teams: Activé", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher"));
        ItemMeta m = choosEnchanted.getItemMeta();
        m.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        choosEnchanted.setItemMeta(m);
        contentManager.setItemForStep(6, 1, new InventoryItemStack(choosEnchanted).setInteractionMethod((player, action) -> {
            contentManager.changeState(6, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("randomiseTeams", true);
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            BaseSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("TeamsSetup")
                                .title(ChatColor.GOLD + "Gérer les équipes")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .onClose((player) -> {
                                    MultiGames.INSTANCE.teamsManager.generateTeams();
                                })
                                .build();

        inv.open(p);
        
        if(teamsNumber > 1) contentManager.changeState(2, 1);
        if(randomTeams){
            contentManager.changeState(6, 0);
        }else{
            contentManager.changeState(6, 1);
        }
        contentManager.getInventory().update(p);
    }
}
