package net.darkaqua.blacksmith.mod.world;

import net.darkaqua.blacksmith.api.util.Vect2i;
import net.darkaqua.blacksmith.api.world.generation.IWorldGeneratorDefinition;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by cout970 on 18/12/2015.
 */
public class BS_WorldGenerator implements IWorldGenerator{

    private IWorldGeneratorDefinition def;

    public BS_WorldGenerator(IWorldGeneratorDefinition def){
        this.def = def;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        def.generateChunk(MCInterface.fromWorld(world), MCInterface.fromIChunkProvider(chunkGenerator), MCInterface.fromIChunkProvider(chunkProvider), random, new Vect2i(chunkX, chunkZ));
    }
}
