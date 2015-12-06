package net.darkaqua.blacksmith.mod.event.modloader;

import net.darkaqua.blacksmith.api.event.modloader.IPreInitEvent;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Created by cout970 on 07/11/2015.
 */
public class BS_PreInitEvent implements IPreInitEvent{

    private FMLPreInitializationEvent event;

    public BS_PreInitEvent(FMLPreInitializationEvent event) {
        this.event = event;
    }

    @Override
    public boolean isEventCancelable() {
        return false;
    }

    @Override
    public void setEventCanceled(boolean value) {}

    @Override
    public boolean isEventCanceled() {
        return false;
    }

    @Override
    public boolean hasEventResult() {
        return false;
    }

    @Override
    public EventResult getEventResult() {
        return EventResult.DEFAULT;
    }

    @Override
    public Object getForgeEvent() {
        return event;
    }

    @Override
    public File getModConfigurationDirectory() {
        return event.getModConfigurationDirectory();
    }

    @Override
    public File getSuggestedConfigurationFile() {
        return new File(event.getModConfigurationDirectory(), ModLoaderManager.getActiveMod().getModId()+".cfg");
    }

    @Override
    public File getSourceFile() {
        return ModLoaderManager.getActiveMod().getSource();
    }

    @Override
    public Logger getModLog() {
        return event.getModLog();
    }
}
