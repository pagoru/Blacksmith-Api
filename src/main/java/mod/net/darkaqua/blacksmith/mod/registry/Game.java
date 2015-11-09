package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.registry.IBlockRegistry;
import net.darkaqua.blacksmith.api.registry.IGame;

/**
 * Created by cout970 on 08/11/2015.
 */
public class Game implements IGame{

    public static final Game INSTANCE = new Game();

    private Game(){}

    @Override
    public IBlockRegistry getBlockRegistry() {
        return BlockRegistry.INSTANCE;
    }
}
