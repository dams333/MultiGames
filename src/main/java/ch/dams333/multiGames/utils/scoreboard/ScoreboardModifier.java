package ch.dams333.multiGames.utils.scoreboard;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.scoreboard.ScoreboardSetupInventory;

public class ScoreboardModifier {
    public int currentLine;
    public boolean isWriting;


    public ScoreboardModifier() {
        currentLine = 1;
        isWriting = false;
    }


    public void writted(Player player, String message) {
        Map<Integer, String> scoreboard = new HashMap<>();
        if(MultiGames.INSTANCE.gameVariablesManager.getVariable("scoreboard").getValue() instanceof HashMap<?, ?>){
            HashMap<?, ?> map = (HashMap<?, ?>) MultiGames.INSTANCE.gameVariablesManager.getVariable("scoreboard").getValue();
            for(Object index : map.keySet()){
                if(index instanceof Integer){
                    scoreboard.put((Integer) index, (String) map.get(index));
                }
            }
        }
        scoreboard.put(currentLine - 1, message);
        MultiGames.INSTANCE.gameVariablesManager.setValue("scoreboard", scoreboard);
        isWriting = false;
        ScoreboardSetupInventory.open(player);
    }
}
