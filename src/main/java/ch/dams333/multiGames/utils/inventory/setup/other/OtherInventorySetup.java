package ch.dams333.multiGames.utils.inventory.setup.other;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.BaseSetupInventory;
import ch.dams333.multiGames.utils.items.ItemCreator;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;

public class OtherInventorySetup {
    public static void open(Player p){
        ContentManager contentManager = new ContentManager();

        contentManager.setItemForStep(1, 0, new InventoryItemStack(Material.COMPASS, ChatColor.GOLD + "Spectateurs: Désactivée", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher")).setInteractionMethod((player, action) -> {
            contentManager.changeState(1, 1);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateSpec", true);
        }));
        ItemStack enchanted = ItemCreator.create(Material.COMPASS, ChatColor.GOLD + "Spectateurs: Activée", Arrays.asList(ChatColor.GRAY + "Clique gauche pour switcher"));
        ItemMeta m = enchanted.getItemMeta();
        m.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        enchanted.setItemMeta(m);
        contentManager.setItemForStep(1, 1, new InventoryItemStack(enchanted).setInteractionMethod((player, action) -> {
            contentManager.changeState(1, 0);
            contentManager.getInventory().update(p);
            MultiGames.INSTANCE.gameVariablesManager.setValue("activateSpec", false);
        }));



        int specTime = MultiGames.INSTANCE.gameVariablesManager.getVariable("reconnectionTime").getIntValue();

        List<String> lore = Arrays.asList(ChatColor.GREEN + "Clique gauche pour augmenter", ChatColor.RED + "Clique droit pour réduire");

        contentManager.setDefaultItem(3, new InventoryItemStack(Material.WATCH, ChatColor.GOLD + "Temps pour se reconnecter: " + specTime + "s", lore).setInteractionMethod((player, action) -> {
            if(action == InventoryAction.PICKUP_ALL){
                //+
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("reconnectionTime").getIntValue() + 30;
                if(newLimit > 3600){
                    newLimit = 3600;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("reconnectionTime", newLimit);
                contentManager.changeItemName(3, 0, ChatColor.GOLD + "Temps pour se reconnecter: " + newLimit + "s");
                contentManager.getInventory().update(p);
            }
            if(action == InventoryAction.PICKUP_HALF){
                //-
                int newLimit = MultiGames.INSTANCE.gameVariablesManager.getVariable("reconnectionTime").getIntValue() - 30;
                if(newLimit < 0){
                    newLimit = 0;
                }
                MultiGames.INSTANCE.gameVariablesManager.setValue("reconnectionTime", newLimit);
                contentManager.changeItemName(3, 0, ChatColor.GOLD + "Temps pour se reconnecter: " + newLimit + "s");
                contentManager.getInventory().update(p);
            }
        }));

        contentManager.setDefaultItem(5, new InventoryItemStack(Material.PAPER, ChatColor.GOLD + "Choisir le nom de la partie", Arrays.asList(ChatColor.GRAY + "Nom actuel:", ChatColor.GRAY + MultiGames.INSTANCE.gameVariablesManager.getVariable("gameName").getStringValue())).setInteractionMethod((player, action) -> {
            
            if(MultiGames.INSTANCE.scoreboardModifiers.containsKey(p)){
                MultiGames.INSTANCE.scoreboardModifiers.remove(p);
            }
            
            MultiGames.INSTANCE.gameVariablesManager.isChangingName = p;
            p.closeInventory();
            p.sendMessage(ChatColor.LIGHT_PURPLE + "Entrer le nom de la partie dans le chat:");
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            BaseSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("OtherSetup")
                                .title(ChatColor.GOLD + "Autres")
                                .rows(1)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);


        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("activateSpec").getBooleanValue()){
            contentManager.changeState(1, 1);
        }else{
            contentManager.changeState(1, 0);
        }
        contentManager.getInventory().update(p);
    }
}
