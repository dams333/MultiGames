package ch.dams333.multiGames.utils.world;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import ch.dams333.multiGames.MultiGames;

public class WorldPregenerator {

    public static void pregenerate(Player p) {
        int radius = MultiGames.INSTANCE.gameVariablesManager.getVariable("startBorderSize").getIntValue();
        World world = p.getLocation().getWorld();
        p.sendMessage(ChatColor.LIGHT_PURPLE + "Démarrage de la prégénération dans un rayon de " + radius + " blocs...");
    }
    
}
