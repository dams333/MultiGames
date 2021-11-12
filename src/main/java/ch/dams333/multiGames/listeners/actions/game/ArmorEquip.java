package ch.dams333.multiGames.listeners.actions.game;

import com.codingforcookies.armorequip.ArmorEquipEvent;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import ch.dams333.multiGames.MultiGames;

public class ArmorEquip implements Listener {
    private MultiGames main;
    public ArmorEquip(MultiGames main) {
        this.main = main;
    }

    private int countIron(Player p){
        int count = 0;
        if(p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType() == Material.IRON_HELMET) count++;
        if(p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) count++;
        if(p.getInventory().getLeggings() != null && p.getInventory().getLeggings().getType() == Material.IRON_LEGGINGS) count++;
        if(p.getInventory().getBoots() != null && p.getInventory().getBoots().getType() == Material.IRON_BOOTS) count++;
        return count;
    }
    private int countDiamond(Player p){
        int count = 0;
        if(p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType() == Material.DIAMOND_HELMET) count++;
        if(p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE) count++;
        if(p.getInventory().getLeggings() != null && p.getInventory().getLeggings().getType() == Material.DIAMOND_LEGGINGS) count++;
        if(p.getInventory().getBoots() != null && p.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS) count++;
        return count;
    }

    private boolean isIron(ItemStack it){
        if(it.getType() == Material.IRON_HELMET) return true;
        if(it.getType() == Material.IRON_CHESTPLATE) return true;
        if(it.getType() == Material.IRON_LEGGINGS) return true;
        if(it.getType() == Material.IRON_BOOTS) return true;
        return false;
    }
    private boolean isDiamond(ItemStack it){
        if(it.getType() == Material.DIAMOND_HELMET) return true;
        if(it.getType() == Material.DIAMOND_CHESTPLATE) return true;
        if(it.getType() == Material.DIAMOND_LEGGINGS) return true;
        if(it.getType() == Material.DIAMOND_BOOTS) return true;
        return false;
    }

    @EventHandler
    public void onArmorEquip(ArmorEquipEvent e) {
        Player p = e.getPlayer();
        if(e.getNewArmorPiece() != null && e.getNewArmorPiece().getType() != Material.AIR){
            if(isIron(e.getNewArmorPiece())){
                if(countIron(p) >= main.gameVariablesManager.getVariable("ironArmorLimit").getIntValue()){
                    e.setCancelled(true);
                    p.getInventory().addItem(e.getNewArmorPiece());
                    p.updateInventory();
                    p.closeInventory();
                    p.sendMessage(ChatColor.RED + "Tu ne peut pas équiper plus de " + main.gameVariablesManager.getVariable("ironArmorLimit").getIntValue() + " pièces en fer");
                }
            }
            if(isDiamond(e.getNewArmorPiece())){
                if(countDiamond(p) >= main.gameVariablesManager.getVariable("diamondArmorLimit").getIntValue()){
                    e.setCancelled(true);
                    p.getInventory().addItem(e.getNewArmorPiece());
                    p.updateInventory();
                    p.closeInventory();
                    p.sendMessage(ChatColor.RED + "Tu ne peut pas équiper plus de " + main.gameVariablesManager.getVariable("diamondArmorLimit").getIntValue() + " pièces en diamant");
                }
            }
        }
    }

}
