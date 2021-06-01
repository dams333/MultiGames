package ch.dams333.multiGames.utils.inventory.setup.border;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.items.ItemCreator;

public class ChangeBorderSetupInventory {

    public static void open(Player p, String variable, String name){

        int smallModificator = 10;
        int mediumModificator = 50;
        int bigModificator = 100;

        int min = 10;
        int max = 10000;

        Material item = Material.BARRIER;

        ContentManager contentManager = new ContentManager();

        ItemStack plusBanner = ItemCreator.create(Material.BANNER, (byte) 10);
        BannerMeta plusMeta = (BannerMeta) plusBanner.getItemMeta();
        plusMeta.addPattern(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
        plusMeta.addPattern(new Pattern(DyeColor.BLACK, PatternType.STRIPE_CENTER));
        plusBanner.setItemMeta(plusMeta);

        ItemStack minusBanner = ItemCreator.create(Material.BANNER, (byte) 1);
        BannerMeta minusMeta = (BannerMeta) minusBanner.getItemMeta();
        minusMeta.addPattern(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
        minusBanner.setItemMeta(minusMeta);

        contentManager.setDefaultItem(0, new InventoryItemStack(ItemCreator.create(minusBanner, "- " + bigModificator)).setInteractionMethod((player, action) -> {
            int current = MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue();
            current -= bigModificator;
            if(current < min) current = min;

            MultiGames.INSTANCE.gameVariablesManager.setValue(variable, current);

            contentManager.changeItemName(4, 0, name.replaceAll("%SIZE%", String.valueOf(MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue())));
            contentManager.getInventory().update(p);
        }));
        contentManager.setDefaultItem(1, new InventoryItemStack(ItemCreator.create(minusBanner, "- " + mediumModificator)).setInteractionMethod((player, action) -> {
            int current = MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue();
            current -= mediumModificator;
            if(current < min) current = min;

            MultiGames.INSTANCE.gameVariablesManager.setValue(variable, current);
            
            contentManager.changeItemName(4, 0, name.replaceAll("%SIZE%", String.valueOf(MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue())));
            contentManager.getInventory().update(p);
        }));
        contentManager.setDefaultItem(2, new InventoryItemStack(ItemCreator.create(minusBanner, "- " + smallModificator)).setInteractionMethod((player, action) -> {
            int current = MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue();
            current -= smallModificator;
            if(current < min) current = min;

            MultiGames.INSTANCE.gameVariablesManager.setValue(variable, current);
            
            contentManager.changeItemName(4, 0, name.replaceAll("%SIZE%", String.valueOf(MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue())));
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(4, new InventoryItemStack(item, name.replaceAll("%SIZE%", String.valueOf(MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue()))));

        contentManager.setDefaultItem(6, new InventoryItemStack(ItemCreator.create(plusBanner, "+ " + smallModificator)).setInteractionMethod((player, action) -> {
            int current = MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue();
            current += smallModificator;
            if(current > max) current = max;

            MultiGames.INSTANCE.gameVariablesManager.setValue(variable, current);
            
            contentManager.changeItemName(4, 0, name.replaceAll("%SIZE%", String.valueOf(MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue())));
            contentManager.getInventory().update(p);
        }));
        contentManager.setDefaultItem(7, new InventoryItemStack(ItemCreator.create(plusBanner, "+ " + mediumModificator)).setInteractionMethod((player, action) -> {
            int current = MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue();
            current += mediumModificator;
            if(current > max) current = max;

            MultiGames.INSTANCE.gameVariablesManager.setValue(variable, current);
            
            contentManager.changeItemName(4, 0, name.replaceAll("%SIZE%", String.valueOf(MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue())));
            contentManager.getInventory().update(p);
        }));
        contentManager.setDefaultItem(8, new InventoryItemStack(ItemCreator.create(plusBanner, "+ " + bigModificator)).setInteractionMethod((player, action) -> {
            int current = MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue();
            current += bigModificator;
            if(current > max) current = max;

            MultiGames.INSTANCE.gameVariablesManager.setValue(variable, current);
            
            contentManager.changeItemName(4, 0, name.replaceAll("%SIZE%", String.valueOf(MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue())));
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(22, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            BorderSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("BorderSetup")
                                .title(ChatColor.GOLD + "Gestion de la bordure")
                                .rows(3)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);
    }
    
}
