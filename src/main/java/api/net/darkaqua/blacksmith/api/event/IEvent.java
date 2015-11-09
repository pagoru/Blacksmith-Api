package net.darkaqua.blacksmith.api.event;

/**
 * Created by cout970 on 07/11/2015.
 */
public interface IEvent {

    boolean isEventCancelable();

    void setEventCanceled(boolean value);

    boolean isEventCanceled();

    boolean hasEventResult();

    EventResult getEventResult();

    public static enum EventResult {
        DENY, DEFAULT, ALLOW
    }
}
