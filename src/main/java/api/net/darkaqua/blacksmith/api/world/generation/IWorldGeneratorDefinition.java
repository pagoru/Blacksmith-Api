package net.darkaqua.blacksmith.api.world.generation;

import net.darkaqua.blacksmith.api.util.Vect2i;
import net.darkaqua.blacksmith.api.world.IIChunkProvider;
import net.darkaqua.blacksmith.api.world.IWorld;

import java.util.Random;

/**
 * Created by cout970 on 18/12/2015.
 */
public interface IWorldGeneratorDefinition {

    void generateChunk(IWorld world, IIChunkProvider chunkGenerator, IIChunkProvider chunkProvider, Random chunkRandom, Vect2i chunkPos);
}
