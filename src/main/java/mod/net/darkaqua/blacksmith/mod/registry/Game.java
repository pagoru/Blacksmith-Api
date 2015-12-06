package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.registry.IBlockRegistry;
import net.darkaqua.blacksmith.api.registry.IGame;
import net.darkaqua.blacksmith.api.registry.ITileEntityRegistry;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

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

    @Override
    public ITileEntityRegistry getTileEntityRegistry() {
        return TileEntityRegistry.INSTANCE;
    }

    @Override
    public boolean isClient() {
        return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT;
    }

    @Override
    public boolean isServer() {
        return FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER;
    }

    @Override
    public boolean isDeobfuscatedEnvironment() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }
}
