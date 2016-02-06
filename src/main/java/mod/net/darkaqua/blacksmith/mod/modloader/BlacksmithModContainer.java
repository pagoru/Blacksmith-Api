package net.darkaqua.blacksmith.mod.modloader;

import com.google.common.base.Strings;
import com.google.common.eventbus.Subscribe;
import net.darkaqua.blacksmith.api.modloader.ModInstance;
import net.darkaqua.blacksmith.api.modloader.ModSidedProxy;
import net.darkaqua.blacksmith.mod.registry.InterModRegistry;
import net.darkaqua.blacksmith.mod.util.Log;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.ModCandidate;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;

/**
 * Created by cout970 on 06/02/2016.
 */
public class BlacksmithModContainer extends FMLModContainerWrapper {

    public BlacksmithModContainer(String className, ModCandidate container, Map<String, Object> modDescriptor) {
        super(className, container, modDescriptor);
    }

    @Subscribe
    public void constructMod(FMLConstructionEvent event) {
        super.constructMod(event);
        Class<?> clazz = getMod().getClass();
        ModLoaderManager.registerMod(this, getMod());
        try {
            for (Field f : clazz.getDeclaredFields()) {
                if (f.isAnnotationPresent(ModInstance.class)) {
                    ModInstance ann = f.getAnnotation(ModInstance.class);
                    if (ann.value().equals("")) {
                        f.set(getMod(), getMod());
                    } else {
                        if (Loader.isModLoaded(ann.value())) {
                            ModContainer mc = Loader.instance().getIndexedModList().get(ann.value());
                            f.set(getMod(), mc.getMod());
                        }
                    }
                }
            }
        } catch (ReflectiveOperationException e) {
            Log.warn("Error trying to place a mod instance in a field marked with @ModInstance: " + clazz);
        }
        try {
            for (Field f : clazz.getDeclaredFields()) {
                if (f.isAnnotationPresent(net.darkaqua.blacksmith.api.modloader.ModIdentifier.class)) {
                    f.set(getMod(), ModLoaderManager.getModIdentifier(getMod()));
                }
            }
        } catch (ReflectiveOperationException e) {
            Log.warn("Error trying to place a mod identifier in a field marked with @ModIdentifier: " + clazz);
        }
        CustomProxyInjector.inject(this, event.getASMHarvestedData(), FMLCommonHandler.instance().getSide(), getLanguageAdapter2());
        InterModRegistry.onModConstructs(event.getASMHarvestedData());

    }

    @Override
    public Map<String, String> getSharedModDescriptor() {
        Map<String, String> sup = super.getSharedModDescriptor();
        sup.put("modsystem", "Blacksmith");
        return sup;
    }

    public static class CustomProxyInjector {

        public static void inject(ModContainer mod, ASMDataTable data, Side side, ILanguageAdapter languageAdapter) {

            //Adapted to use @ModSidedProxy instead @SideProxy

            FMLLog.fine("Attempting to inject @ModSidedProxy classes into %s", mod.getModId());
            Set<ASMDataTable.ASMData> targets = data.getAnnotationsFor(mod).get(ModSidedProxy.class.getName());
            ModClassLoader mcl = Loader.instance().getModClassLoader();

            for (ASMDataTable.ASMData targ : targets) {
                try {
                    Class<?> proxyTarget = Class.forName(targ.getClassName(), true, mcl);
                    Field target = proxyTarget.getDeclaredField(targ.getObjectName());
                    if (target == null) {
                        // Impossible?
                        FMLLog.severe("Attempted to load a proxy type into %s.%s but the field was not found", targ.getClassName(), targ.getObjectName());
                        throw new LoaderException(String.format("Attempted to load a proxy type into %s.%s but the field was not found", targ.getClassName(), targ.getObjectName()));
                    }
                    target.setAccessible(true);

                    ModSidedProxy annotation = target.getAnnotation(ModSidedProxy.class);
                    if (!Strings.isNullOrEmpty(annotation.modId()) && !annotation.modId().equals(mod.getModId())) {
                        FMLLog.fine("Skipping proxy injection for %s.%s since it is not for mod %s", targ.getClassName(), targ.getObjectName(), mod.getModId());
                        continue;
                    }
                    String targetType = side.isClient() ? annotation.clientSide() : annotation.serverSide();
                    Object proxy = Class.forName(targetType, true, mcl).newInstance();

                    if (languageAdapter.supportsStatics() && (target.getModifiers() & Modifier.STATIC) == 0) {
                        FMLLog.severe("Attempted to load a proxy type %s into %s.%s, but the field is not static", targetType, targ.getClassName(), targ.getObjectName());
                        throw new LoaderException(String.format("Attempted to load a proxy type %s into %s.%s, but the field is not static", targetType, targ.getClassName(), targ.getObjectName()));
                    }
                    if (!target.getType().isAssignableFrom(proxy.getClass())) {
                        FMLLog.severe("Attempted to load a proxy type %s into %s.%s, but the types don't match", targetType, targ.getClassName(), targ.getObjectName());
                        throw new LoaderException(String.format("Attempted to load a proxy type %s into %s.%s, but the types don't match", targetType, targ.getClassName(), targ.getObjectName()));
                    }
                    languageAdapter.setProxy(target, proxyTarget, proxy);
                } catch (Exception e) {
                    FMLLog.log(Level.ERROR, e, "An error occurred trying to load a proxy into %s.%s", targ.getAnnotationInfo(), targ.getClassName(), targ.getObjectName());
                    throw new LoaderException(e);
                }
            }

            // Allow language specific proxy injection.
            languageAdapter.setInternalProxies(mod, side, mcl);
        }
    }
}
