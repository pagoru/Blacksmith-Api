package net.darkaqua.blacksmith.mod;

import com.google.common.eventbus.Subscribe;
import net.darkaqua.blacksmith.api.Game;
import net.darkaqua.blacksmith.api.common.modloader.BlacksmithMod;
import net.darkaqua.blacksmith.mod.client.creativetab.BS_CreativeTabFactory;
import net.darkaqua.blacksmith.mod.client.render.BS_TileEntityRenderer;
import net.darkaqua.blacksmith.mod.client.sound.BS_SoundEffectFactory;
import net.darkaqua.blacksmith.mod.common.block.BS_BlockMaterialFactory;
import net.darkaqua.blacksmith.mod.common.block.blockdata.BS_BlockDataFactory;
import net.darkaqua.blacksmith.mod.common.config.BS_ConfigurationFactory;
import net.darkaqua.blacksmith.mod.common.entity.BS_EntityFactory;
import net.darkaqua.blacksmith.mod.common.event.BS_EventBus;
import net.darkaqua.blacksmith.mod.common.event.FMLEventRedirect;
import net.darkaqua.blacksmith.mod.common.fluid.BS_FluidStackFactory;
import net.darkaqua.blacksmith.mod.common.inventory.BS_ItemStackFactory;
import net.darkaqua.blacksmith.mod.common.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.common.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.common.network.BS_NetworkChannelFactory;
import net.darkaqua.blacksmith.mod.common.registry.*;
import net.darkaqua.blacksmith.mod.common.storage.BS_DataElementFactory;
import net.darkaqua.blacksmith.mod.common.tileentity.BS_TileEntity;
import net.darkaqua.blacksmith.mod.common.util.BS_ObjectScanner;
import net.darkaqua.blacksmith.mod.common.util.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModContainerFactory;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.objectweb.asm.Type;

/**
 * Created by cout970 on 07/11/2015.
 */

public class Blacksmith extends DummyModContainer {

    public static Blacksmith INSTANCE;
    public static final String MOD_ID = "blacksmith";
    public static final String MOD_NAME = "Blacksmith";
    public static final String MOD_VERSION = "@VERSION@";

    public Blacksmith() {
        super(new ModMetadata());
        INSTANCE = this;
        System.out.println("Blacksmith instance created");
        ModContainerFactory.instance().registerContainerType(Type.getType(BlacksmithMod.class), BlacksmithModContainer.class);
        BS_EventBus.init();
        BSGame.init();
    }

    //  Events code

    @Subscribe
    public void preInit(FMLPreInitializationEvent event) {
        Log.init();
        try {
            Log.info("[BLACKSMITH] Starting Blacksmith...");
            BS_ItemStackFactory.init();
            BS_CreativeTabFactory.init();
            BS_ConfigurationFactory.init();
            BS_ObjectScanner.init();
            BS_DataElementFactory.init();
            BS_BlockMaterialFactory.init();
            BS_BlockDataFactory.init();
            BS_EntityFactory.init();
            BS_SoundEffectFactory.init();
            FMLEventRedirect.init();
            BS_FluidStackFactory.init();
            BS_NetworkChannelFactory.init();
            Log.info("[BLACKSMITH] Starting done.");

            if (Game.isClient()) {
                IReloadableResourceManager manager = (IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager();
                MinecraftForge.EVENT_BUS.register(RenderRegistry.INSTANCE);
                MinecraftForge.EVENT_BUS.register(ModelRegistry.INSTANCE);
                manager.registerReloadListener(ResourceManager.INSTANCE);
                manager.registerReloadListener(RenderRegistry.INSTANCE);
                ClientRegistry.bindTileEntitySpecialRenderer(BS_TileEntity.class, BS_TileEntityRenderer.INSTANCE);
            }
            GameRegistry.registerTileEntity(BS_TileEntity.class, "Blacksmith_TE");
            InterModRegistry.registerDefaultInterfaces();
            Log.info("[BLACKSMITH] Starting PreInitEvent");
            ModLoaderManager.firePreInit(event);
            Log.info("[BLACKSMITH] PreInitEvent done");
        } catch (Exception e) {
            Log.error("Error in Blacksmith preInit, please report this to the authors");
            e.printStackTrace();
        }
    }


    @Subscribe
    public void init(FMLInitializationEvent event) {
        try {
            if (Game.isClient()) {
                RenderManager.init();
            }
            Log.info("[BLACKSMITH] Starting InitEvent");
            ModLoaderManager.fireInit(event);
            Log.info("[BLACKSMITH] InitEvent done");
        } catch (Exception e) {
            Log.error("Error in Blacksmith Init, please report this to the authors");
            e.printStackTrace();
        }

    }

    @Subscribe
    public void postInit(FMLPostInitializationEvent event) {
        try {
            Log.info("Starting PostInitEvent");
            ModLoaderManager.firePostInit(event);
            Log.info("PostInitEvent done");
        } catch (Exception e) {
            Log.error("Error in Blacksmith postInit, please report this to the authors");
            e.printStackTrace();
        }
    }

    //  DummyModContainer code

    @Override
    public boolean registerBus(com.google.common.eventbus.EventBus bus, LoadController controller) {
        bus.register(INSTANCE);
        return true;
    }

    @Override
    public Object getMod() {
        return INSTANCE;
    }

    @Override
    public String getModId() {
        return MOD_ID;
    }

    @Override
    public String getName() {
        return MOD_NAME;
    }

    @Override
    public String getVersion() {
        return MOD_VERSION;
    }

    @Override
    public boolean matches(Object mod) {
        return mod == INSTANCE;
    }

    @Override
    public String getDisplayVersion() {
        return getVersion();
    }

    @Override
    public boolean isImmutable() {
        return false;
    }
}
