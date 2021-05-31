package ch.dams333.multiGames.utils.items;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemCreator {
    
    public static ItemStack create(Material material){
        return create(material, 1);
    }
    public static ItemStack create(Material material, int quantity){
        return create(material, quantity, (byte) 0);
    }
    public static ItemStack create(Material material, byte data){
        return create(material, 1, data);
    }
    public static ItemStack create(Material material, int quantity, byte data){
        return new ItemStack(material, quantity, data);
    }
    public static ItemStack create(Material material, String name){
        return create(material, 1, (byte) 0, name);
    }
    public static ItemStack create(Material material, int quantity, String name){
        return create(material, quantity, (byte) 0, name);
    }
    public static ItemStack create(Material material, byte data, String name){
        return create(material, 1, data, name);
    }
    public static ItemStack create(Material material, int quantity, byte data, String name){
        ItemStack it = new ItemStack(material, quantity, data);
        ItemMeta itM = it.getItemMeta();
        itM.setDisplayName(name);
        it.setItemMeta(itM);
        return it;
    }
    public static ItemStack create(Material material, List<String> lore){
        return create(material, 1, (byte) 0, lore);
    }
    public static ItemStack create(Material material, int quantity, List<String> lore){
        return create(material, quantity, (byte) 0, lore);
    }
    public static ItemStack create(Material material, byte data, List<String> lore){
        return create(material, 1, data, lore);
    }
    public static ItemStack create(Material material, String name, List<String> lore){
        return create(material, 1, (byte) 0, name, lore);
    }
    public static ItemStack create(Material material, int quantity, byte data, List<String> lore){
        ItemStack it = new ItemStack(material, quantity, data);
        ItemMeta itM = it.getItemMeta();
        itM.setLore(lore);
        it.setItemMeta(itM);
        return it;
    }
    public static ItemStack create(Material material, int quantity, String name, List<String> lore){
        return create(material, quantity, (byte) 0, name, lore);
    }
    public static ItemStack create(Material material, byte data, String name, List<String> lore){
        return create(material, 1, data, name, lore);
    }
    public static ItemStack create(Material material, int quantity, byte data, String name, List<String> lore){
        ItemStack it = new ItemStack(material, quantity, data);
        ItemMeta itM = it.getItemMeta();
        itM.setDisplayName(name);
        itM.setLore(lore);
        it.setItemMeta(itM);
        return it;
    }

    public static ItemStack create(ItemStack it, String name){
        ItemMeta m = it.getItemMeta();
        m.setDisplayName(name);
        ItemStack returnIt = it.clone();
        returnIt.setItemMeta(m);
        return returnIt;
    }

}
