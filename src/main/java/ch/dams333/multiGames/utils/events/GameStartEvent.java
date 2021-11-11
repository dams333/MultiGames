package ch.dams333.multiGames.utils.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameStartEvent extends Event{

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    
}
