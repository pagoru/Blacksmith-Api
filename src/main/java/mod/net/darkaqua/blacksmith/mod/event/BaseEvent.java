package net.darkaqua.blacksmith.mod.event;

import net.darkaqua.blacksmith.api.event.IEvent;

/**
 * Created by cout970 on 06/12/2015.
 */
public class BaseEvent implements IEvent {

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
}
