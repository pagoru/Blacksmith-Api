package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.registry.*;
import net.darkaqua.blacksmith.api.server.IServerHandler;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by cout970 on 08/11/2015.
 */
public class Game implements IGame {

    public static final Game INSTANCE = new Game();

    private Game() {
    }

    @Override
    public IBlockRegistry getBlockRegistry() {
        return BlockRegistry.INSTANCE;
    }

    @Override
    public IItemRegistry getItemRegistry() {
        return ItemRegistry.INSTANCE;
    }

    @Override
    public IRenderRegistry getRenderRegistry() {
        return RenderRegistry.INSTANCE;
    }

    @Override
    public ITileEntityRegistry getTileEntityRegistry() {
        return TileEntityRegistry.INSTANCE;
    }

    @Override
    public IWorldGenerationRegistry getWorldGenerationRegistry() {
        return WorldGenerationRegistry.INSTANCE;
    }

    @Override
    public IResourceManager getResourceManager() {
        return ResourceManager.INSTANCE;
    }

    @Override
    public IRenderManager getRenderManager() {
        return RenderManager.INSTANCE;
    }

    @Override
    public IOreDictionary getOreDictionary() {
        return OreDictionaryManager.INSTANCE;
    }

    @Override
    public IFluidRegistry getFluidRegistry() {
        return FluidRegistryManager.INSTANCE;
    }

    @Override
    public IRecipeRegistry getRecipeRegistry() {
        return RecipeRegistry.INSTANCE;
    }

    @Override
    public IGuiRegistry getGuiRegistry() {
        return GuiRegistry.INSTANCE;
    }

    @Override
    public IInterModRegistry getInterModRegistry() {
        return InterModRegistry.INSTANCE;
    }

    @Override
    public IParticleManager getParticleManager() {
        return ParticleManager.INSTANCE;
    }

    @Override
    public ISoundHandler getSoundHandler() {
        return SoundHandler.INSTANCE;
    }

    @Override
    public IServerHandler getServer() {
        return ServerHandler.INSTANCE;
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
    public boolean isDedicatedServer() {
        return FMLCommonHandler.instance().getSide() == Side.SERVER;
    }

    @Override
    public boolean isDeobfuscatedEnvironment() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }
}
