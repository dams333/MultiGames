package ch.dams333.multiGames.utils.world;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;

import ch.dams333.multiGames.MultiGames;
import net.minecraft.server.v1_12_R1.BiomeBase;
import net.minecraft.server.v1_12_R1.Biomes;

public class WorldGenerator {
    
    private boolean generateOceans;
    private boolean generateDeserts;
    private boolean generateSpawn;

    public WorldGenerator() {
        this.generateOceans = false;
        this.generateDeserts = false;
        this.generateSpawn = true;
    }

    public boolean isGenerateOceans() {
        return this.generateOceans;
    }

    public boolean getGenerateOceans() {
        return this.generateOceans;
    }

    public void setGenerateOceans(boolean generateOceans) {
        this.generateOceans = generateOceans;
    }

    public boolean isGenerateDeserts() {
        return this.generateDeserts;
    }

    public boolean getGenerateDeserts() {
        return this.generateDeserts;
    }

    public void setGenerateDeserts(boolean generateDeserts) {
        this.generateDeserts = generateDeserts;
    }

    public boolean isGenerateSpawn() {
        return this.generateSpawn;
    }

    public boolean getGenerateSpawn() {
        return this.generateSpawn;
    }

    public void setGenerateSpawn(boolean generateSpawn) {
        this.generateSpawn = generateSpawn;
    }

    public void generateWorld(Player p){

        MultiGames.INSTANCE.worldGenerators.remove(p);

        /*BiomeMappingAPI api = new BiomeMappingAPI();
        try {
            api.replaceBiomes(Biome.OCEAN, Biome.PLAINS);
            api.replaceBiomes(Biome.DESERT, Biome.PLAINS);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        WorldCreator worldCreator = new WorldCreator(UUID.randomUUID().toString());
        
        World world = worldCreator.createWorld();
        world.setDifficulty(Difficulty.NORMAL);
        world.getWorldBorder().setCenter(0, 0);
        world.getWorldBorder().setSize(30000000);

        p.teleport(world.getSpawnLocation());
    }



}

