package net.darkaqua.blacksmith.mod.event.modloader;

import net.darkaqua.blacksmith.api.event.IEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Created by cout970 on 07/11/2015.
 */
public class InitEvent implements IEvent{

    private FMLInitializationEvent event;

    public InitEvent(FMLInitializationEvent event) {
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
