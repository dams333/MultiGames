package ch.dams333.multiGames.utils.state;

public class GameStateManager {

    private GameState gameState;

    public GameStateManager() {
        gameState = GameState.SETUP;
    }

    public boolean isState(GameState gameState){
        return this.gameState == gameState;
    }

    public void setState(GameState gameState){
        this.gameState = gameState;
    }
    
}
