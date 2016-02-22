package net.darkaqua.blacksmith.api.common.event;

/**
 * Created by cout970 on 07/11/2015.
 */
public interface IEvent {

    boolean isEventCancelable();

    void setEventCanceled(boolean value);

    boolean isEventCanceled();

    boolean hasEventResult();

    EventResult getEventResult();

    enum EventResult {
        DENY, DEFAULT, ALLOW
    }
}
