package net.darkaqua.blacksmith.mod;

import com.google.common.eventbus.Subscribe;
import net.darkaqua.blacksmith.api.event.EventBus;
import net.darkaqua.blacksmith.api.modloader.BlacksmithMod;
import net.darkaqua.blacksmith.api.registry.StaticAccess;
import net.darkaqua.blacksmith.api.util.Log;
import net.darkaqua.blacksmith.mod.creativetab.BS_CreativeTabFactory;
import net.darkaqua.blacksmith.mod.event.BS_EventBus;
import net.darkaqua.blacksmith.mod.event.modloader.InitEvent;
import net.darkaqua.blacksmith.mod.event.modloader.PostInitEvent;
import net.darkaqua.blacksmith.mod.event.modloader.PreInitEvent;
import net.darkaqua.blacksmith.mod.inventory.BS_ItemStackFactory;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.registry.Game;
import net.darkaqua.blacksmith.mod.util.BS_Log;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModContainerFactory;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.objectweb.asm.Type;

import java.util.Map;

/**
 * Created by cout970 on 07/11/2015.
 */
@IFMLLoadingPlugin.MCVersion("1.8")
public class Blacksmith extends DummyModContainer implements IFMLLoadingPlugin {

    public static Blacksmith INSTANCE;
    public static final String MOD_ID = "Blacksmith";
    public static final String MOD_NAME = "Blacksmith";
    public static final String MOD_VERSION = "0.0.0";

    public Blacksmith() {
        super(new ModMetadata());
        ModContainerFactory.instance().registerContainerType(Type.getType(BlacksmithMod.class), BlacksmithModContainer.class);
        INSTANCE = this;
        BS_Log.init();
        BS_ItemStackFactory.init();
        BS_CreativeTabFactory.init();
        BS_EventBus.init();
        StaticAccess.GAME = Game.INSTANCE;
    }

    //  Events code

    @Subscribe
    public void preInit(FMLPreInitializationEvent event) {
        Log.info("Starting PreInitEvent");
        EventBus.postEvent(new PreInitEvent(event));
        Log.info("PreInitEvent done");
    }

    @Subscribe
    public void Init(FMLInitializationEvent event) {
        Log.info("Starting InitEvent");
        EventBus.postEvent(new InitEvent(event));
        Log.info("InitEvent done");
    }

    @Subscribe
    public void postInit(FMLPostInitializationEvent event) {
        Log.info("Starting PostInitEvent");
        EventBus.postEvent(new PostInitEvent(event));
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
        return this;
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
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}
