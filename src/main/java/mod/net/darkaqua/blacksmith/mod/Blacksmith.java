package net.darkaqua.blacksmith.mod;

import com.google.common.eventbus.Subscribe;
import net.darkaqua.blacksmith.api.modloader.BlacksmithMod;
import net.darkaqua.blacksmith.api.registry.StaticAccess;
import net.darkaqua.blacksmith.mod.block.BS_BlockMaterialFactory;
import net.darkaqua.blacksmith.mod.block.blockdata.BS_BlockDataFactory;
import net.darkaqua.blacksmith.mod.config.BS_ConfigurationFactory;
import net.darkaqua.blacksmith.mod.creativetab.BS_CreativeTabFactory;
import net.darkaqua.blacksmith.mod.event.BS_EventBus;
import net.darkaqua.blacksmith.mod.event.FMLEventRedirect;
import net.darkaqua.blacksmith.mod.fluid.BS_FluidStackFactory;
import net.darkaqua.blacksmith.mod.inventory.BS_ItemStackFactory;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.network.BS_NetworkChannelFactory;
import net.darkaqua.blacksmith.mod.network.packet.BS_PacketFactory;
import net.darkaqua.blacksmith.mod.registry.*;
import net.darkaqua.blacksmith.mod.render.BS_TileEntityRenderer;
import net.darkaqua.blacksmith.mod.storage.BS_DataElementFactory;
import net.darkaqua.blacksmith.mod.tileentity.BS_TileEntity;
import net.darkaqua.blacksmith.mod.util.BS_ObjectScanner;
import net.darkaqua.blacksmith.mod.util.Log;
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
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.objectweb.asm.Type;

import java.util.Map;

/**
 * Created by cout970 on 07/11/2015.
 */
@IFMLLoadingPlugin.MCVersion("1.8.9")
public class Blacksmith extends DummyModContainer implements IFMLLoadingPlugin {

    public static Blacksmith INSTANCE;
    public static final String MOD_ID = "blacksmith";
    public static final String MOD_NAME = "Blacksmith";
    public static final String MOD_VERSION = "0.0.0";

    public Blacksmith() {
        super(new ModMetadata());
        ModContainerFactory.instance().registerContainerType(Type.getType(BlacksmithMod.class), BlacksmithModContainer.class);
        INSTANCE = this;
        Log.init();
        BS_ItemStackFactory.init();
        BS_CreativeTabFactory.init();
        BS_EventBus.init();
        BS_ConfigurationFactory.init();
        BS_ObjectScanner.init();
        BS_DataElementFactory.init();
        BS_PacketFactory.init();
        BS_BlockMaterialFactory.init();
        BS_BlockDataFactory.init();
        StaticAccess.GAME = Game.INSTANCE;
    }

    public static void debug() {
        Log.debug("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        Log.debug("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    //  Events code

    @Subscribe
    public void preInit(FMLPreInitializationEvent event) {
        Log.info("Starting PreInitEvent");
        try {
            FMLEventRedirect.init();
            BS_FluidStackFactory.init();
            BS_NetworkChannelFactory.init();
            IReloadableResourceManager manager = (IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager();
            manager.registerReloadListener(ResourceManager.INSTANCE);
            if (Game.INSTANCE.isClient()) {
                MinecraftForge.EVENT_BUS.register(RenderRegistry.INSTANCE);
                MinecraftForge.EVENT_BUS.register(ModelRegistry.INSTANCE);
                ClientRegistry.bindTileEntitySpecialRenderer(BS_TileEntity.class, BS_TileEntityRenderer.INSTANCE);
            }
            GameRegistry.registerTileEntity(BS_TileEntity.class, "Blacksmith_TE");
            ModLoaderManager.firePreInit(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.info("PreInitEvent done");
    }

    @Subscribe
    public void Init(FMLInitializationEvent event) {
        Log.info("Starting InitEvent");
        try {
            if (Game.INSTANCE.isClient()) {
                RenderManager.init();
            }
            ModLoaderManager.fireInit(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.info("InitEvent done");
    }

    @Subscribe
    public void postInit(FMLPostInitializationEvent event) {
        Log.info("Starting PostInitEvent");
        ModLoaderManager.firePostInit(event);
        Log.info("PostInitEvent done");
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

    //  IFMLLoadingPlugin code

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        return "net.darkaqua.blacksmith.mod.Blacksmith";
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}
