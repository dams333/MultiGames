package ch.dams333.multiGames.listeners.drop;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

import ch.dams333.multiGames.MultiGames;

public class AppleDrop implements Listener {

    private MultiGames main;

    public AppleDrop(MultiGames main) {
        this.main = main;
    }

    @EventHandler
    public void onLeavesDecayEvent(LeavesDecayEvent e) {
        Block b = e.getBlock();
        Location loc = new Location(b.getWorld(), (double)b.getLocation().getBlockX() + 0.0D, (double)b.getLocation().getBlockY() + 0.0D, (double)b.getLocation().getBlockZ() + 0.0D);
        Random random = new Random();
        double r = random.nextDouble();
        if (r <= main.gameVariablesManager.getVariable("appleDropRate").getFloatValue() * 0.01D && b.getType() == Material.LEAVES) {
            b.setType(Material.AIR);
            b.getWorld().dropItemNaturally(loc, new ItemStack(Material.APPLE, 1));
        }

    }

   @EventHandler
   public void onBlockBreakEvent(BlockBreakEvent e) {
      Block Block = e.getBlock();
      Location loc = new Location(Block.getWorld(), (double)((float)Block.getLocation().getBlockX() + 0.0F), (double)((float)Block.getLocation().getBlockY() + 0.0F), (double)((float)Block.getLocation().getBlockZ() + 0.0F));
      Random random = new Random();
      double r = random.nextDouble();
      if (r <= main.gameVariablesManager.getVariable("appleDropRate").getFloatValue() * 0.005D && Block.getType() == Material.LEAVES && e.getPlayer().getInventory().getItemInMainHand().getType() != Material.SHEARS) {
         Block.setType(Material.AIR);
         Block.getWorld().dropItemNaturally(loc, new ItemStack(Material.APPLE, 1));
      }
   }
    
}
