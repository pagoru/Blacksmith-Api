package net.darkaqua.blacksmith.mod.common.registry;

import net.darkaqua.blacksmith.api.common.registry.IWorldGenerationRegistry;
import net.darkaqua.blacksmith.api.common.world.generation.IWorldGeneratorDefinition;
import net.darkaqua.blacksmith.mod.common.world.BS_WorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 18/12/2015.
 */
public class WorldGenerationRegistry implements IWorldGenerationRegistry {

    public static final WorldGenerationRegistry INSTANCE = new WorldGenerationRegistry();
    private static final List<IWorldGeneratorDefinition> generators = new LinkedList<>();

    private WorldGenerationRegistry() {
    }

    @Override
    public boolean registerWorldGenerator(IWorldGeneratorDefinition def, int priority) {
        if (def == null || priority <= 0) {
            return false;
        }
        generators.add(def);
        GameRegistry.registerWorldGenerator(new BS_WorldGenerator(def), priority);
        return true;
    }
}
