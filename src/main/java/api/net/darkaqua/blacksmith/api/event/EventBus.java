package net.darkaqua.blacksmith.api.event;

/**
 * Created by cout970 on 07/11/2015.
 */
public abstract class EventBus {

    protected static EventBus INSTANCE;

    /**
     * Send an event to all registered event listeners that are associated to this event
     *
     * @param event the event to post
     * @return true if the event is not cancelled, and false otherwise
     */
    public static boolean postEvent(IEvent event) {
        return INSTANCE.post(event);
    }

    /**
     * Registers a instance of an object which class has one or more methods with the following conditions:
     * - The method must have an @EventSubscribe annotation
     * - The method must have only one argument and this argument defines which events will accept
     * <p>
     * Example 1:
     * <p>
     * This method will be called every time an event is fired in the bus and the event
     * class is InitEvent or any subclass of InitEvent
     *
     * @param obj instance of an object with one or more event listeners
     * @return true if the listener has been registered successfully, false otherwise
     * @EventSubscribe public void onEvent(InitEvent e){}
     * <p>
     * Example 2:
     * <p>
     * This method will be called every time an event if fired on this bus:
     * @EventSubscribe public void onEvent(IEvent e){}
     */
    public static boolean registerEventListener(Object obj) {
        return INSTANCE.registerListener(obj);
    }

    protected abstract boolean post(IEvent event);

    protected abstract boolean registerListener(Object obj);
}
