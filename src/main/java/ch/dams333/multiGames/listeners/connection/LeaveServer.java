package ch.dams333.multiGames.listeners.connection;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.events.PlayerLeaveEvent;
import ch.dams333.multiGames.utils.state.GameState;

public class LeaveServer implements Listener {
    private MultiGames main;

    public LeaveServer(MultiGames main) {
        this.main = main;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        if(main.gameStateManager.isState(GameState.GAME)){
            if (main.gameManager.isAlive(e.getPlayer())) {
                Bukkit.getServer().getPluginManager().callEvent(new PlayerLeaveEvent(e.getPlayer()));
            }
        }
    }
}
