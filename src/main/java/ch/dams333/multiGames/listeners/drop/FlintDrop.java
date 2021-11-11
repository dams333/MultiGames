package ch.dams333.multiGames.listeners.drop;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import ch.dams333.multiGames.MultiGames;

public class FlintDrop implements Listener {

    private MultiGames main;

    public FlintDrop(MultiGames main) {
        this.main = main;
    }

   @EventHandler
   public void onBlockBreakEvent(BlockBreakEvent e) {
      Block Block = e.getBlock();
      Location loc = new Location(Block.getWorld(), (double)((float)Block.getLocation().getBlockX() + 0.0F), (double)((float)Block.getLocation().getBlockY() + 0.0F), (double)((float)Block.getLocation().getBlockZ() + 0.0F));
      Random random = new Random();
      double r = random.nextDouble();
      if (r <= main.gameVariablesManager.getVariable("flintDropRate").getFloatValue() * 0.01D && Block.getType() == Material.GRAVEL) {
        Block.setType(Material.AIR);
        Block.getWorld().dropItemNaturally(loc, new ItemStack(Material.FLINT));
     }
   }
    
}
