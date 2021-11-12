package ch.dams333.multiGames.listeners.actions.game;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;

import ch.dams333.multiGames.MultiGames;

public class EnchantEvent implements Listener{
    private MultiGames main;

    public EnchantEvent(MultiGames main) {
        this.main = main;
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
    public void enchantEvent(EnchantItemEvent e){
        if(e.getItem().getType() != Material.AIR){
            if(isIron(e.getItem())) {
                for (Enchantment enchantment : e.getEnchantsToAdd().keySet()) {
                    if(enchantment.getName().equals("PROTECTION_ENVIRONMENTAL")){
                        if(e.getEnchantsToAdd().get(enchantment) > main.gameVariablesManager.getVariable("ironProtectionLimit").getIntValue()){
                            e.setCancelled(true);
                            e.getEnchanter().sendMessage(ChatColor.RED + "Tu ne peut pas mettre un niveau de protection supérieur à " + main.gameVariablesManager.getVariable("ironProtectionLimit").getIntValue() + " sur du fer");
                        }
                    }
                }
            }
            if(isDiamond(e.getItem())){
                for (Enchantment enchantment : e.getEnchantsToAdd().keySet()) {
                    if(enchantment.getName().equals("PROTECTION_ENVIRONMENTAL")){
                        if(e.getEnchantsToAdd().get(enchantment) > main.gameVariablesManager.getVariable("diamondProtectionLimit").getIntValue()){
                            e.setCancelled(true);
                            e.getEnchanter().sendMessage(ChatColor.RED + "Tu ne peut pas mettre un niveau de protection supérieur à " + main.gameVariablesManager.getVariable("diamondProtectionLimit") + " sur du diamant");
                        }
                    }
                }
            }
            if(e.getItem().getType() == Material.IRON_SWORD){
                for (Enchantment enchantment : e.getEnchantsToAdd().keySet()) {
                    if(enchantment.getName().equals("DAMAGE_ALL")){
                        if(e.getEnchantsToAdd().get(enchantment) > main.gameVariablesManager.getVariable("ironSharpnessLimit").getIntValue()){
                            e.setCancelled(true);
                            e.getEnchanter().sendMessage(ChatColor.RED + "Tu ne peut pas mettre un niveau de tranchant supérieur à " + main.gameVariablesManager.getVariable("ironSharpnessLimit").getIntValue() + " sur du fer");
                        }
                    }
                }
            }
            if(e.getItem().getType() == Material.DIAMOND_SWORD){
                for (Enchantment enchantment : e.getEnchantsToAdd().keySet()) {
                    if(enchantment.getName().equals("DAMAGE_ALL")){
                        if(e.getEnchantsToAdd().get(enchantment) > main.gameVariablesManager.getVariable("diamondSharpnessLimit").getIntValue()){
                            e.setCancelled(true);
                            e.getEnchanter().sendMessage(ChatColor.RED + "Tu ne peut pas mettre un niveau de tranchant supérieur à " + main.gameVariablesManager.getVariable("diamondSharpnessLimit") + " sur du diamant");
                        }
                    }
                }
            }
            if(!e.isCancelled()){
                for(Enchantment enchantment : e.getEnchantsToAdd().keySet()){
                    if(enchantment.getName().equals("ARROW_FIRE") || enchantment.getName().equals("FIRE_ASPECT")){
                        if(!main.gameVariablesManager.getVariable("activateFireEnchant").getBooleanValue()){
                            e.setCancelled(true);
                            e.getEnchanter().sendMessage(ChatColor.RED + "Les enchantements de feu sont désactivés !");
                        }
                    }
                }
            }
        }
    }
}
