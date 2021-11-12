package ch.dams333.multiGames.core.game.border;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.events.GameUpdateTimeEvent;

public class BorderManager implements Listener {

    private MultiGames main;

    private int size;
    private WorldBorder border;

    public BorderManager(MultiGames main) {
        this.main = main;
        size = main.gameVariablesManager.getVariable("startBorderSize").getIntValue();
    }

    public int getSize() {
        return this.size;
    }

    public void setupBorder() {
        this.border = main.gameManager.getGameWorld().getWorldBorder();
        border.setCenter(new Location(main.gameManager.getGameWorld(), 0, 0, 0));
        border.setSize(size * 2);
        border.setWarningDistance(0);
        border.setDamageAmount(1);
        border.setDamageBuffer(0);
    }

    @EventHandler
    public void time(GameUpdateTimeEvent e){
        if(e.getTime() == main.gameVariablesManager.getVariable("startBorderTime").getIntValue()){
            Bukkit.broadcastMessage(ChatColor.RED + "La bordure commence à réduire");
            border.setSize(main.gameVariablesManager.getVariable("stopBorderSize").getIntValue() * 2, main.gameVariablesManager.getVariable("stopBorderTime").getIntValue() - main.gameVariablesManager.getVariable("startBorderTime").getIntValue());
        }
        this.size = Integer.parseInt(String.valueOf(Math.round(border.getSize() / 2)));
    }
}
