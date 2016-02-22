package net.darkaqua.blacksmith.mod.common.event;

import net.darkaqua.blacksmith.api.common.event.EventBus;
import net.darkaqua.blacksmith.api.common.event.EventSubscribe;
import net.darkaqua.blacksmith.mod.common.event.config.BS_ConfigChangedEvent;
import net.darkaqua.blacksmith.mod.common.event.config.BS_PostConfigChangedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * Created by cout970 on 06/12/2015.
 */
public class FMLEventRedirect {

    public static final FMLEventRedirect INSTANCE = new FMLEventRedirect();

    public static void init() {
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        FMLCommonHandler.instance().bus().register(INSTANCE);
    }

    @EventSubscribe
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent e) {
        EventBus.postEvent(new BS_ConfigChangedEvent(e));
    }

    @EventSubscribe
    public void postConfigChangedEvent(ConfigChangedEvent.PostConfigChangedEvent e) {
        EventBus.postEvent(new BS_PostConfigChangedEvent(e));
    }
}
