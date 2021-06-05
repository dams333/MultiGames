package ch.dams333.multiGames.utils.variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.ItemStack;

public class GameVariablesManager {

    private List<GameVariable> variables;

    public GameVariablesManager() {
        variables = new ArrayList<>();
        initVariables();
    }

    public void initVariables(){
        this.variables.add(new GameVariable("invicibilityTime", 60));
        this.variables.add(new GameVariable("pvpTime", 1200));
        this.variables.add(new GameVariable("startBorderTime", 3600));
        this.variables.add(new GameVariable("stopBorderTime", 5400));
        this.variables.add(new GameVariable("delayPVP", false));
        this.variables.add(new GameVariable("activateRod", true));
        this.variables.add(new GameVariable("activateRegen", false));
        this.variables.add(new GameVariable("startBorderSize", 2000));
        this.variables.add(new GameVariable("stopBorderSize", 100));
        this.variables.add(new GameVariable("activateNether", true));
        this.variables.add(new GameVariable("activateEnd", false));
        this.variables.add(new GameVariable("ironLimit", 0));
        this.variables.add(new GameVariable("goldLimit", 0));
        this.variables.add(new GameVariable("diamondLimit", 0));
        this.variables.add(new GameVariable("startInventory", new ArrayList<ItemStack>()));
        this.variables.add(new GameVariable("ironProtectionLimit", 4));
        this.variables.add(new GameVariable("diamondProtectionLimit", 4));
        this.variables.add(new GameVariable("ironSharpnessLimit", 5));
        this.variables.add(new GameVariable("diamondSharpnessLimit", 5));
        this.variables.add(new GameVariable("activateFireEnchant", true));
        this.variables.add(new GameVariable("ironArmorLimit", 4));
        this.variables.add(new GameVariable("diamondArmorLimit", 4));
        this.variables.add(new GameVariable("appleDropRate", 0.5));
        this.variables.add(new GameVariable("flintDropRate", 10.0));
        this.variables.add(new GameVariable("teamsNumber", 1));
        this.variables.add(new GameVariable("randomiseTeams", false));
        this.variables.add(new GameVariable("announceDeath", true));
        this.variables.add(new GameVariable("announceKiller", true));
        this.variables.add(new GameVariable("viewHeadHealth", true));
        this.variables.add(new GameVariable("viewTabHealth", true));
        this.variables.add(new GameVariable("activateGlobalChat", true));
        this.variables.add(new GameVariable("activateAnonymousGlobalChat", false));
        this.variables.add(new GameVariable("activateTeamChat", true));
        this.variables.add(new GameVariable("activateAnonymousTeamChat", false));
        this.variables.add(new GameVariable("scoreboard", new HashMap<Integer, String>()));
        this.variables.add(new GameVariable("gameName", "Custom Game"));
        this.variables.add(new GameVariable("activateSpec", true));
        this.variables.add(new GameVariable("reconnectionTime", 60));
    }

    public GameVariable getVariable(String name){
        for(GameVariable var : this.variables){
            if(var.getName().equals(name)){
                return var;
            }
        }
        return null;
    }

    public void setValue(String name, Object value){
        for(GameVariable var : this.variables){
            if(var.getName().equals(name)){
                this.variables.remove(var);
                var.setValue(value);
                this.variables.add(var);
                return;
            }
        }
    }

    public Player isChangingName;
    
}
