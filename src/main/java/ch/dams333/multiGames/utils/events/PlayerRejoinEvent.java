package ch.dams333.multiGames.utils.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerRejoinEvent extends Event{

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private Player ancienPlayer;
    


    public PlayerRejoinEvent(Player player, Player ancienPlayer) {
        this.player = player;
        this.ancienPlayer = ancienPlayer;
    }

    public Player getAncienPlayer() {
        return this.ancienPlayer;
    }
    

    public Player getPlayer() {
        return this.player;
    }


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    
}
