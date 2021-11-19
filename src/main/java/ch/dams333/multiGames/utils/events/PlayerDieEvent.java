package ch.dams333.multiGames.utils.events;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerDieEvent extends Event{

    private static final HandlerList handlers = new HandlerList();

    private UUID player;
    private Player killer;
    

    public PlayerDieEvent(UUID player, Player killer) {
        this.player = player;
        this.killer = killer;
    }

    public UUID getPlayer() {
        return this.player;
    }

    public Player getKiller() {
        return this.killer;
    }



    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    
}
