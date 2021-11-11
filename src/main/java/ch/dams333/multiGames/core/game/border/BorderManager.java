package ch.dams333.multiGames.core.game.border;

import ch.dams333.multiGames.MultiGames;

public class BorderManager {

    private MultiGames main;

    private int size;

    public BorderManager(MultiGames main) {
        this.main = main;
        size = main.gameVariablesManager.getVariable("startBorderSize").getIntValue();
    }


    public int getSize() {
        return this.size;
    }


    public void setupBorder() {
    }

    
}
