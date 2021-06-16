package ch.dams333.multiGames.utils.variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.inventory.setup.preconfiguration.PreconfigurationSetupInventory;

public class GameVariablesManager {

    private List<GameVariable> variables;
    private List<GameConfiguration> configurations;

    public GameVariablesManager() {
        variables = new ArrayList<>();
        configurations = new ArrayList<>();
        initVariables();
        loadConfig();
    }

    private void loadConfig() {

        MultiGames main = MultiGames.INSTANCE;

        for(String stringUUID : main.getConfig().getKeys(false)){
            ConfigurationSection sec = main.getConfig().getConfigurationSection(stringUUID);
            String name = sec.getString("Name");
            Material mat = Material.valueOf(sec.getString("Material"));
            List<GameVariable> configVars = new ArrayList<>();
            ConfigurationSection varSec = sec.getConfigurationSection("Variables");

            for(String varName : varSec.getKeys(false)){
                if(!varSec.isConfigurationSection(varName)){
                    Object obj = varSec.get(varName);
                    if(obj instanceof String){
                        configVars.add(new GameVariable(varName, (String) obj));
                    }else if(obj instanceof Integer){
                        configVars.add(new GameVariable(varName, (Integer) obj));
                    }else if(obj instanceof Double){
                        configVars.add(new GameVariable(varName, (Double) obj));
                    }else if(obj instanceof Float){
                        configVars.add(new GameVariable(varName, (Float) obj));
                    }else if(obj instanceof Boolean){
                        configVars.add(new GameVariable(varName, (Boolean) obj));
                    }
                }else{
                    if(varName.equals("startInventory")){
                        ConfigurationSection invSec = varSec.getConfigurationSection(varName);

                        List<ItemStack> items = new ArrayList<>();
                        for(String i : invSec.getKeys(false)){
                            ItemStack it = invSec.getItemStack(i);
                            items.add(it);
                        }

                        configVars.add(new GameVariable(varName, items));
                    }

                    if(varName.equals("scoreboard")){
                        ConfigurationSection scoreboardSec = varSec.getConfigurationSection(varName);
                        HashMap<Integer, String> scoreboard = new HashMap<Integer, String>();
                        for(String i : scoreboardSec.getKeys(false)){
                            scoreboard.put(Integer.parseInt(i), scoreboardSec.getString(i));
                        }

                        configVars.add(new GameVariable(varName, scoreboard));
                    }
                }
            }
            GameConfiguration config = new GameConfiguration(UUID.fromString(stringUUID), name, mat, configVars);
            this.configurations.add(config);
        }

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
    public Player isCreatingConfig;

    public List<GameVariable> getVariables() {
        return this.variables;
    }

    public void createConfig(Player p, Material mat, String name) {
        this.isCreatingConfig = null;
        GameConfiguration config = new GameConfiguration(name, mat);
        configurations.add(config);
        config.save();
        PreconfigurationSetupInventory.open(p);
    }


    public List<GameConfiguration> getConfigurations() {
        return this.configurations;
    }

    public void loadConfiguration(UUID uuid) {
        Map<String, Object> vars = new HashMap<>();
        for(GameConfiguration config : this.configurations){
            if(config.getUuid().equals(uuid)){
                for(GameVariable var : config.getVariables()){
                    vars.put(var.getName(), var.getValue());
                }
            }
        }
        for(String var : vars.keySet()){
            this.setValue(var, vars.get(var));
        }
    }

    public void removeConfiguration(UUID uuid) {
        for(GameConfiguration config : this.configurations){
            if(config.getUuid().equals(uuid)){
                this.configurations.remove(config);
                MultiGames.INSTANCE.getConfig().set(config.getUuid().toString(), null);
                MultiGames.INSTANCE.saveConfig();
                break;
            }
        }
    }

    
}
