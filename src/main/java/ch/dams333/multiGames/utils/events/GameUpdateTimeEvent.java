package ch.dams333.multiGames.utils.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameUpdateTimeEvent extends Event{

    private static final HandlerList handlers = new HandlerList();
    private int time;


    public GameUpdateTimeEvent(int time) {
        this.time = time;
    }

    public int getTime() {
        return this.time;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    
}
