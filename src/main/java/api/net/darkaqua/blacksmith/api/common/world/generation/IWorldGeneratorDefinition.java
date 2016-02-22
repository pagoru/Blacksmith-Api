package net.darkaqua.blacksmith.api.common.world.generation;

import net.darkaqua.blacksmith.api.common.util.vectors.Vect2i;
import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;
import net.darkaqua.blacksmith.api.common.world.IIChunkProvider;
import net.darkaqua.blacksmith.api.common.world.IWorld;

import java.util.Random;

/**
 * Created by cout970 on 18/12/2015.
 */
@Implementable
public interface IWorldGeneratorDefinition {

    void generateChunk(IWorld world, IIChunkProvider chunkGenerator, IIChunkProvider chunkProvider, Random chunkRandom, Vect2i chunkPos);
}
