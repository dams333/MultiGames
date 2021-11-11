package ch.dams333.multiGames.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.items.ItemsSetupInventory;
import ch.dams333.multiGames.utils.items.ItemCreator;
import ch.dams333.multiGames.utils.state.GameState;

public class ValidCommand implements CommandExecutor {

    private MultiGames main;

    public ValidCommand(MultiGames main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if(sender instanceof Player && main.gameStateManager.isState(GameState.SETUP)){
            ArrayList<ItemStack> items = new ArrayList<>();
            for(ItemStack it : ((HumanEntity) sender).getInventory()){
                items.add(it);
            }
            main.gameVariablesManager.setValue("startInventory", items);
            ((HumanEntity) sender).getInventory().clear();
            ((HumanEntity) sender).getInventory().setItem(4, ItemCreator.create(Material.CHEST, ChatColor.GOLD + "Gérer la partie"));
            ItemsSetupInventory.open((Player) sender);
        }
        return false;
    }

    
}
