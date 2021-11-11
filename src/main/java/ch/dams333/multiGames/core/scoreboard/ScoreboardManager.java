package ch.dams333.multiGames.core.scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.events.GameUpdateTimeEvent;
import ch.dams333.multiGames.utils.time.TimeUtils;
import fr.mrmicky.fastboard.FastBoard;

public class ScoreboardManager implements Listener{

    private MultiGames main;

    private Map<Player, FastBoard> boards;

    public ScoreboardManager(MultiGames main) {
        this.main = main;
        boards = new HashMap<>();
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    public void setupScoreboard() {
       
        List<String> viewScoreboard = getScoreboard(0);

        for(Player p : Bukkit.getOnlinePlayers()){
            if(viewScoreboard.size() > 0){
                FastBoard board = new FastBoard(p);
                board.updateTitle(MultiGames.INSTANCE.gameVariablesManager.getVariable("gameName").getStringValue().replaceAll("&", "§"));
                board.updateLines(viewScoreboard);
                boards.put(p, board);
            }
        }
    }

    private List<String> getScoreboard(int time){
        Map<Integer, String> scoreboard = new HashMap<>();
        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("scoreboard").getValue() instanceof HashMap<?, ?>){
            HashMap<?, ?> map = (HashMap<?, ?>) MultiGames.INSTANCE.gameVariablesManager.getVariable("scoreboard").getValue();
            for(Object index : map.keySet()){
                if(index instanceof Integer){
                    scoreboard.put((Integer) index, (String) map.get(index));
                }
            }
        }
       
        List<String> viewScoreboard = new ArrayList<>();

        for(int i = 0; i < 16; i++){
            if(!scoreboard.containsKey(i)){
                boolean isNext = false;
                for(int j : scoreboard.keySet()){
                    if(j > i){
                        if(scoreboard.get(j) != null) isNext = true;
                        break;
                    }
                }
                if(isNext) viewScoreboard.add("");
            }else{
                String toModify = scoreboard.get(i);

                toModify = toModify.replaceAll("&", "§");
                toModify = toModify.replaceAll("%COMPLETE_TIMER%", TimeUtils.secondToCompleteString(time, ":", true));
                toModify = toModify.replaceAll("%TIMER%", TimeUtils.secondToString(time, ":", true));
                toModify = toModify.replaceAll("%IN_GAME_PLAYERS_COUNT%", String.valueOf(main.gameManager.getInGamePlayers().size()));
                toModify = toModify.replaceAll("%BORDER_RADIUS%", String.valueOf(main.gameManager.getBorderManager().getSize()));
                toModify = toModify.replaceAll("%EPISODE%", String.valueOf(main.gameManager.getEpisode()));
                toModify = toModify.replaceAll("%STARTING_PLAYERS_COUNT%", String.valueOf(main.gameManager.getStarterPlayerCount()));
                toModify = toModify.replaceAll("%STARTING_TEAMS_COUNT%", String.valueOf(main.teamsManager.getStartingTeamsCount()));
                toModify = toModify.replaceAll("%TEAMS_COUNT%", String.valueOf(main.teamsManager.getTeamsCount()));

                viewScoreboard.add(toModify);
            }
        }
        return viewScoreboard;
    }

    @EventHandler
    public void update(GameUpdateTimeEvent e){
        List<String> viewScoreboard = getScoreboard(e.getTime());

        for(Player p : Bukkit.getOnlinePlayers()){
            if(viewScoreboard.size() > 0){
                if(!boards.containsKey(p)){
                    FastBoard board = new FastBoard(p);
                    board.updateTitle(MultiGames.INSTANCE.gameVariablesManager.getVariable("gameName").getStringValue().replaceAll("&", "§"));
                    board.updateLines(viewScoreboard);
                    boards.put(p, board);
                }else{
                    FastBoard board = boards.get(p);
                    board.updateLines(viewScoreboard);
                    boards.put(p, board);
                }
            }
        }
    }

    
}
