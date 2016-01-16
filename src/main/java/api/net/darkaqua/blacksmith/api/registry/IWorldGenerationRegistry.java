package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.world.generation.IWorldGeneratorDefinition;

/**
 * Created by cout970 on 18/12/2015.
 */
public interface IWorldGenerationRegistry {

    boolean registerWorldGenerator(IWorldGeneratorDefinition def, int priority);


}
