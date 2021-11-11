package ch.dams333.multiGames.utils.inventory.setup.drops;

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
import ch.dams333.multiGames.utils.inventory.setup.BaseSetupInventory;
import ch.dams333.multiGames.utils.items.ItemCreator;

public class DropsSetupInventory {
    public static void open(Player p){
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

        double appleDrop = MultiGames.INSTANCE.gameVariablesManager.getVariable("appleDropRate").getDoubleValue();
        double flintDrop = MultiGames.INSTANCE.gameVariablesManager.getVariable("flintDropRate").getDoubleValue();

        contentManager.setDefaultItem(0, new InventoryItemStack(ItemCreator.create(minusBanner, ChatColor.GOLD + "- 5%")).setInteractionMethod((player, action) -> {
            Double current = MultiGames.INSTANCE.gameVariablesManager.getVariable("appleDropRate").getDoubleValue();
            current -= 5;
            if(current < 0) current = 0.0;

            MultiGames.INSTANCE.gameVariablesManager.setValue("appleDropRate", current);

            contentManager.changeItemName(4, 0, ChatColor.GOLD + "Drop de pommes: " + current + "%");
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(1, new InventoryItemStack(ItemCreator.create(minusBanner, ChatColor.GOLD + "- 1%")).setInteractionMethod((player, action) -> {
            Double current = MultiGames.INSTANCE.gameVariablesManager.getVariable("appleDropRate").getDoubleValue();
            current -= 1;
            if(current < 0) current = 0.0;

            MultiGames.INSTANCE.gameVariablesManager.setValue("appleDropRate", current);

            contentManager.changeItemName(4, 0, ChatColor.GOLD + "Drop de pommes: " + current + "%");
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(2, new InventoryItemStack(ItemCreator.create(minusBanner, ChatColor.GOLD + "- 0.5%")).setInteractionMethod((player, action) -> {
            Double current = MultiGames.INSTANCE.gameVariablesManager.getVariable("appleDropRate").getDoubleValue();
            current -= 0.5;
            if(current < 0) current = 0.0;

            MultiGames.INSTANCE.gameVariablesManager.setValue("appleDropRate", current);

            contentManager.changeItemName(4, 0, ChatColor.GOLD + "Drop de pommes: " + current + "%");
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(4, new InventoryItemStack(Material.APPLE, ChatColor.GOLD + "Drop de pommes: " + appleDrop + "%"));

        contentManager.setDefaultItem(6, new InventoryItemStack(ItemCreator.create(plusBanner, ChatColor.GOLD + "+ 0.5%")).setInteractionMethod((player, action) -> {
            Double current = MultiGames.INSTANCE.gameVariablesManager.getVariable("appleDropRate").getDoubleValue();
            current += 0.5;
            if(current > 100) current = 100.0;

            MultiGames.INSTANCE.gameVariablesManager.setValue("appleDropRate", current);

            contentManager.changeItemName(4, 0, ChatColor.GOLD + "Drop de pommes: " + current + "%");
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(7, new InventoryItemStack(ItemCreator.create(plusBanner, ChatColor.GOLD + "+ 1%")).setInteractionMethod((player, action) -> {
            Double current = MultiGames.INSTANCE.gameVariablesManager.getVariable("appleDropRate").getDoubleValue();
            current += 1.0;
            if(current > 100) current = 100.0;

            MultiGames.INSTANCE.gameVariablesManager.setValue("appleDropRate", current);

            contentManager.changeItemName(4, 0, ChatColor.GOLD + "Drop de pommes: " + current + "%");
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(8, new InventoryItemStack(ItemCreator.create(plusBanner, ChatColor.GOLD + "+ 5%")).setInteractionMethod((player, action) -> {
            Double current = MultiGames.INSTANCE.gameVariablesManager.getVariable("appleDropRate").getDoubleValue();
            current += 5.0;
            if(current > 100) current = 100.0;

            MultiGames.INSTANCE.gameVariablesManager.setValue("appleDropRate", current);

            contentManager.changeItemName(4, 0, ChatColor.GOLD + "Drop de pommes: " + current + "%");
            contentManager.getInventory().update(p);
        }));


        contentManager.setDefaultItem(9, new InventoryItemStack(ItemCreator.create(minusBanner, ChatColor.GOLD + "- 5%")).setInteractionMethod((player, action) -> {
            Double current = MultiGames.INSTANCE.gameVariablesManager.getVariable("flintDropRate").getDoubleValue();
            current -= 5;
            if(current < 0) current = 0.0;

            MultiGames.INSTANCE.gameVariablesManager.setValue("flintDropRate", current);

            contentManager.changeItemName(13, 0, ChatColor.GOLD + "Drop de silex: " + current + "%");
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(10, new InventoryItemStack(ItemCreator.create(minusBanner, ChatColor.GOLD + "- 1%")).setInteractionMethod((player, action) -> {
            Double current = MultiGames.INSTANCE.gameVariablesManager.getVariable("flintDropRate").getDoubleValue();
            current -= 1;
            if(current < 0) current = 0.0;

            MultiGames.INSTANCE.gameVariablesManager.setValue("flintDropRate", current);

            contentManager.changeItemName(13, 0, ChatColor.GOLD + "Drop de silex: " + current + "%");
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(11, new InventoryItemStack(ItemCreator.create(minusBanner, ChatColor.GOLD + "- 0.5%")).setInteractionMethod((player, action) -> {
            Double current = MultiGames.INSTANCE.gameVariablesManager.getVariable("flintDropRate").getDoubleValue();
            current -= 0.5;
            if(current < 0) current = 0.0;

            MultiGames.INSTANCE.gameVariablesManager.setValue("flintDropRate", current);

            contentManager.changeItemName(13, 0, ChatColor.GOLD + "Drop de silex: " + current + "%");
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(13, new InventoryItemStack(Material.FLINT, ChatColor.GOLD + "Drop de silex: " + flintDrop + "%"));

        contentManager.setDefaultItem(15, new InventoryItemStack(ItemCreator.create(plusBanner, ChatColor.GOLD + "+ 0.5%")).setInteractionMethod((player, action) -> {
            Double current = MultiGames.INSTANCE.gameVariablesManager.getVariable("flintDropRate").getDoubleValue();
            current += 0.5;
            if(current > 100) current = 100.0;

            MultiGames.INSTANCE.gameVariablesManager.setValue("flintDropRate", current);

            contentManager.changeItemName(13, 0, ChatColor.GOLD + "Drop de silex: " + current + "%");
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(16, new InventoryItemStack(ItemCreator.create(plusBanner, ChatColor.GOLD + "+ 1%")).setInteractionMethod((player, action) -> {
            Double current = MultiGames.INSTANCE.gameVariablesManager.getVariable("flintDropRate").getDoubleValue();
            current += 1.0;
            if(current > 100) current = 100.0;

            MultiGames.INSTANCE.gameVariablesManager.setValue("flintDropRate", current);

            contentManager.changeItemName(13, 0, ChatColor.GOLD + "Drop de silex: " + current + "%");
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(17, new InventoryItemStack(ItemCreator.create(plusBanner, ChatColor.GOLD + "+ 5%")).setInteractionMethod((player, action) -> {
            Double current = MultiGames.INSTANCE.gameVariablesManager.getVariable("flintDropRate").getDoubleValue();
            current += 5.0;
            if(current > 100) current = 100.0;

            MultiGames.INSTANCE.gameVariablesManager.setValue("flintDropRate", current);

            contentManager.changeItemName(13, 0, ChatColor.GOLD + "Drop de silex: " + current + "%");
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(31, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            BaseSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                .id("DropsSetup")
                .title(ChatColor.GOLD + "Gérer les drops")
                .rows(4)
                .closeable(true)
                .setContentManager(contentManager)
                .build();

        inv.open(p);
    }
}
