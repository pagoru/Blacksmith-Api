package net.darkaqua.blacksmith.mod.modloader;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.event.EventBus;
import net.darkaqua.blacksmith.api.event.IEvent;
import net.darkaqua.blacksmith.api.modloader.IModIdentifier;
import net.darkaqua.blacksmith.mod.event.BS_EventBus;
import net.darkaqua.blacksmith.mod.event.modloader.BS_InitEvent;
import net.darkaqua.blacksmith.mod.event.modloader.BS_PostInitEvent;
import net.darkaqua.blacksmith.mod.event.modloader.BS_PreInitEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.*;

public class ModLoaderManager {

    private static final List<BlacksmithModContainer> loadedMods = Lists.newArrayList();
    private static final Map<Object, IModIdentifier> modIdentifiers = new IdentityHashMap<>();
    private static BlacksmithModContainer activeMod;
    private static LoadingState currentState = LoadingState.NONE;

    private ModLoaderManager() {
    }

    public static void registerMod(BlacksmithModContainer container, Object instance) {
        loadedMods.add(container);
        modIdentifiers.put(instance, new ModIdentifier(container));
        activeMod = container;
        EventBus.registerEventListener(instance);
        activeMod = null;
    }

    public static String findName(Object plugin) {
        if (plugin == null)
            return null;
        for (BlacksmithModContainer o : loadedMods) {
            if (plugin.equals(o.getMod())) {
                return o.getModId();
            }
        }
        return null;
    }

    public static List<BlacksmithModContainer> getLoadedMods() {
        return new ArrayList<>(loadedMods);
    }

    public static BlacksmithModContainer getActiveMod() {
        return activeMod;
    }

    public static void firePreInit(FMLPreInitializationEvent event) {
        currentState = LoadingState.PREINIT;
        postEvent(new BS_PreInitEvent(event));
    }

    public static void fireInit(FMLInitializationEvent event) {
        currentState = LoadingState.INIT;
        postEvent(new BS_InitEvent(event));
    }

    public static void firePostInit(FMLPostInitializationEvent event) {
        currentState = LoadingState.POSTINIT;
        postEvent(new BS_PostInitEvent(event));
        currentState = LoadingState.NONE;
    }

    private static void postEvent(IEvent e) {
        HashMap<Class<?>, ArrayList<BS_EventBus.SubscribedMethod>> listeners = BS_EventBus.getEventListeners();
        ArrayList<BS_EventBus.SubscribedMethod> methods = null;
        for (Class<?> clazz : listeners.keySet()) {
            if (clazz.isInstance(e)) {
                methods = listeners.get(clazz);
                break;
            }
        }
        if (methods != null) {
            for (BS_EventBus.SubscribedMethod m : methods) {
                activeMod = m.getModOwner();
                m.call(e);
            }
        }
        activeMod = null;
    }

    public static LoadingState getLoadingState() {
        return currentState;
    }

    public static IModIdentifier getModIdentifier(Object instance) {
        return modIdentifiers.get(instance);
    }

    public enum LoadingState {
        PREINIT,
        INIT,
        POSTINIT,
        NONE
    }
}
