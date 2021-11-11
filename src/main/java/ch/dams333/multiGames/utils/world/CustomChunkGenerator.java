package ch.dams333.multiGames.utils.world;

import java.util.Random;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

public class CustomChunkGenerator extends ChunkGenerator{
    
    private boolean generateOceans;
    private boolean generateDeserts;

    public CustomChunkGenerator(boolean generateOceans, boolean generateDeserts) {
        this.generateOceans = generateOceans;
        this.generateDeserts = generateDeserts;
    }


    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {

        int chunkX = 3214 >> 4;
        int chunkZ = 1345 >> 4;

        if (biome.getBiome(chunkX, chunkZ) == Biome.DESERT) {
            if(!generateDeserts){
                biome.setBiome(chunkX, chunkZ, Biome.PLAINS);
            }
        }
        if (biome.getBiome(chunkX, chunkZ) == Biome.OCEAN) {
            if(!generateOceans){
                biome.setBiome(chunkX, chunkZ, Biome.PLAINS);
            }
        }

        return createChunkData(world);
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        int x = 0 , z = 0;
        int y = world.getHighestBlockYAt(x, z);
        return new Location(world, x, y, z);
    }

}
