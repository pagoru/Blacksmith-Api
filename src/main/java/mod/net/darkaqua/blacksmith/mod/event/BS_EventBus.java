package net.darkaqua.blacksmith.mod.event;

import net.darkaqua.blacksmith.api.event.EventBus;
import net.darkaqua.blacksmith.api.event.EventSubscribe;
import net.darkaqua.blacksmith.api.event.IEvent;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.util.BS_Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cout970 on 08/11/2015.
 */
public class BS_EventBus extends EventBus{

    private static HashMap<Class<?>, ArrayList<SubscribedMethod>> listeners = new HashMap<Class<?>, ArrayList<SubscribedMethod>>();

    private BS_EventBus() {}

    public static void init() {
        INSTANCE = new BS_EventBus();
    }

    public static HashMap<Class<?>, ArrayList<SubscribedMethod>> getEventListeners(){
        return listeners;
    }

    @Override
    protected boolean post(IEvent e) {
        ArrayList<SubscribedMethod> methods = null;
        for (Class<?> clazz : listeners.keySet()) {
            if (clazz.isInstance(e)) {
                methods = listeners.get(clazz);
                break;
            }
        }
        if (methods != null) {
            for (SubscribedMethod m : methods) {
                m.call(e);
            }
        }
        return !e.isEventCanceled();
    }

    @Override
    protected boolean registerListener(Object o) {
        if (o == null)
            return false;
        boolean registered = false;
        for (Method m : o.getClass().getMethods()) {

            if (m.isAnnotationPresent(EventSubscribe.class)) {
                Class<?>[] parameterTypes = m.getParameterTypes();
                if (parameterTypes.length != 1) {
                    BS_Log.error("Invalid number of arguments on the EventSubscribe method: " + m.getName());
                    continue;
                }
                Class<?> eventType = parameterTypes[0];
                if (!IEvent.class.isAssignableFrom(eventType)) {
                    BS_Log.error("Invalid argument type on the EventSubscribe method: " + m.getName()
                            + ", the type " + eventType + " don't implements Event");
                    continue;
                }

                register(eventType, o, m);
                registered = true;
            }
        }
        return registered;
    }

    private void register(Class<?> eventType, Object o, Method m) {
        ArrayList<SubscribedMethod> list;
        if (listeners.containsKey(eventType)) {
            list = listeners.get(eventType);
        } else {
            list = new ArrayList<>();
            listeners.put(eventType, list);
        }
        SubscribedMethod caller = new SubscribedMethod(o, m, ModLoaderManager.getActiveMod());
        list.add(caller);
    }

    public static class SubscribedMethod {

        private Object obj;
        private Method method;
        private BlacksmithModContainer owner;

        public SubscribedMethod(Object o, Method m, BlacksmithModContainer owner) {
            obj = o;
            method = m;
            this.owner = owner;
        }

        public Object getInstance() {
            return obj;
        }

        public Method getMethod() {
            return method;
        }

        public BlacksmithModContainer getModOwner() {
            return owner;
        }

        public void call(Object arg) {
            try {
                method.invoke(obj, arg);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
