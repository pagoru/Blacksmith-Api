package net.darkaqua.blacksmith.mod.event.modloader;

import net.darkaqua.blacksmith.api.event.IEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

/**
 * Created by cout970 on 07/11/2015.
 */
public class PostInitEvent implements IEvent{

    private FMLPostInitializationEvent event;

    public PostInitEvent(FMLPostInitializationEvent event) {
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
        return null;
    }
}
