package ch.dams333.multiGames.utils.inventory.setup.timer;

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
import ch.dams333.multiGames.utils.time.TimeUtils;

public class ChangeTimerSetupInventory {

    public static void open(Player p, String variable, Material item, String name, int smallModificator, int mediumModificator, int bigModificator, int min, int max){
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

        contentManager.setDefaultItem(0, new InventoryItemStack(ItemCreator.create(minusBanner, "- " + TimeUtils.secondToCompleteString(bigModificator, ":", true))).setInteractionMethod((player, action) -> {
            int current = MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue();
            current -= bigModificator;
            if(current < min) current = min;

            MultiGames.INSTANCE.gameVariablesManager.setValue(variable, current);

            contentManager.changeItemName(4, 0, name.replaceAll("%TIME%", TimeUtils.secondToCompleteString(MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue(), ":", true)));
            contentManager.getInventory().update(p);
        }));
        contentManager.setDefaultItem(1, new InventoryItemStack(ItemCreator.create(minusBanner, "- " + TimeUtils.secondToCompleteString(mediumModificator, ":", true))).setInteractionMethod((player, action) -> {
            int current = MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue();
            current -= mediumModificator;
            if(current < min) current = min;

            MultiGames.INSTANCE.gameVariablesManager.setValue(variable, current);
            
            contentManager.changeItemName(4, 0, name.replaceAll("%TIME%", TimeUtils.secondToCompleteString(MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue(), ":", true)));
            contentManager.getInventory().update(p);
        }));
        contentManager.setDefaultItem(2, new InventoryItemStack(ItemCreator.create(minusBanner, "- " + TimeUtils.secondToCompleteString(smallModificator, ":", true))).setInteractionMethod((player, action) -> {
            int current = MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue();
            current -= smallModificator;
            if(current < min) current = min;

            MultiGames.INSTANCE.gameVariablesManager.setValue(variable, current);
            
            contentManager.changeItemName(4, 0, name.replaceAll("%TIME%", TimeUtils.secondToCompleteString(MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue(), ":", true)));
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(4, new InventoryItemStack(item, name.replaceAll("%TIME%", TimeUtils.secondToCompleteString(MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue(), ":", true))));

        contentManager.setDefaultItem(6, new InventoryItemStack(ItemCreator.create(plusBanner, "+ " + TimeUtils.secondToCompleteString(smallModificator, ":", true))).setInteractionMethod((player, action) -> {
            int current = MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue();
            current += smallModificator;
            if(current > max) current = max;

            MultiGames.INSTANCE.gameVariablesManager.setValue(variable, current);
            
            contentManager.changeItemName(4, 0, name.replaceAll("%TIME%", TimeUtils.secondToCompleteString(MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue(), ":", true)));
            contentManager.getInventory().update(p);
        }));
        contentManager.setDefaultItem(7, new InventoryItemStack(ItemCreator.create(plusBanner, "+ " + TimeUtils.secondToCompleteString(mediumModificator, ":", true))).setInteractionMethod((player, action) -> {
            int current = MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue();
            current += mediumModificator;
            if(current > max) current = max;

            MultiGames.INSTANCE.gameVariablesManager.setValue(variable, current);
            
            contentManager.changeItemName(4, 0, name.replaceAll("%TIME%", TimeUtils.secondToCompleteString(MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue(), ":", true)));
            contentManager.getInventory().update(p);
        }));
        contentManager.setDefaultItem(8, new InventoryItemStack(ItemCreator.create(plusBanner, "+ " + TimeUtils.secondToCompleteString(bigModificator, ":", true))).setInteractionMethod((player, action) -> {
            int current = MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue();
            current += bigModificator;
            if(current > max) current = max;

            MultiGames.INSTANCE.gameVariablesManager.setValue(variable, current);
            
            contentManager.changeItemName(4, 0, name.replaceAll("%TIME%", TimeUtils.secondToCompleteString(MultiGames.INSTANCE.gameVariablesManager.getVariable(variable).getIntValue(), ":", true)));
            contentManager.getInventory().update(p);
        }));

        contentManager.setDefaultItem(22, new InventoryItemStack(Material.ARROW, ChatColor.GRAY + "Retour dans le menu").setInteractionMethod((player, action) -> {
            TimerSetupInventory.open(p);
        }));

        SimpleInventory inv = SimpleInventory.builder()
                                .id("TimeSetup")
                                .title(ChatColor.GOLD + "Gestion du temps")
                                .rows(3)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();

        inv.open(p);
    }
    
}
