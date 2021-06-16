package ch.dams333.multiGames.utils.variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import ch.dams333.multiGames.MultiGames;

public class GameConfiguration {

    private UUID uuid;
    private String name;
    private Material material;
    private List<GameVariable> variables;


    public GameConfiguration(UUID uuid, String name, Material material, List<GameVariable> variables) {
        this.uuid = uuid;
        this.name = name;
        this.material = material;
        this.variables = variables;
    }


    public GameConfiguration(String name, Material material) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.material = material;
        this.variables = MultiGames.INSTANCE.gameVariablesManager.getVariables();
    }


    public UUID getUuid() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }

    public Material getMaterial() {
        return this.material;
    }

    public List<GameVariable> getVariables() {
        return this.variables;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof GameConfiguration){
            return ((GameConfiguration)obj).getUuid().equals(this.uuid);
        }
        return false;
    }


    public void save() {
        MultiGames main = MultiGames.INSTANCE;

        if(main.getConfig().isConfigurationSection(uuid.toString())){
            main.getConfig().set(uuid.toString(), null);
        }

        ConfigurationSection sec = main.getConfig().createSection(uuid.toString());
        sec.set("Name", this.name);
        sec.set("Material", this.material.toString());

        ConfigurationSection varSec = sec.createSection("Variables");

        for(GameVariable var : this.variables){
            if(var.getValue() instanceof String){
                varSec.set(var.getName(), (String)var.getValue());
            }else if(var.getValue() instanceof Integer){
                varSec.set(var.getName(), (Integer)var.getValue());
            }else if(var.getValue() instanceof Double){
                varSec.set(var.getName(), (Double)var.getValue());
            }else if(var.getValue() instanceof Float){
                varSec.set(var.getName(), (Float)var.getValue());
            }else if(var.getValue() instanceof Boolean){
                varSec.set(var.getName(), (Boolean)var.getValue());
            }else if(var.getValue() instanceof ArrayList<?>){
                ArrayList<?> list = (ArrayList<?>) var.getValue();
                ConfigurationSection stackSec = varSec.createSection(var.getName());
                int i = 0;

                for(Object obj : list){
                    if(obj instanceof ItemStack){
                        ItemStack it = (ItemStack) obj;
                        stackSec.set("Item " + i, it);
                        i++;
                    }
                }
            }else if(var.getValue() instanceof HashMap<?, ?>){
                HashMap<?, ?> map = (HashMap<?, ?>) var.getValue();
                ConfigurationSection mapSec = varSec.createSection(var.getName());
                for(Object obj : map.keySet()){
                    if(obj instanceof Integer){
                        if(map.get(obj) instanceof String){
                            mapSec.set(String.valueOf((Integer)obj), (String) map.get(obj));
                        }
                    }
                }
            }
        }

        main.saveConfig();
    }

    
}
