package ch.dams333.multiGames.core.teams;

import ch.dams333.multiGames.MultiGames;

public class TeamsManager {
    
    private MultiGames main;

    public TeamsManager(MultiGames main) {
        this.main = main;
    }

    public boolean activatedTeams(){
        return false;
    }

    public int getStartingTeamsCount() {
        return 0;
    }

    public int getTeamsCount() {
        return 0;
    }

}
