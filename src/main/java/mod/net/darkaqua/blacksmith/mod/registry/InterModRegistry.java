package net.darkaqua.blacksmith.mod.registry;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.intermod.IInterfaceIdentifier;
import net.darkaqua.blacksmith.api.intermod.InterfaceIdentifierHolder;
import net.darkaqua.blacksmith.api.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.inventory.defaults.SimpleInventoryHandler;
import net.darkaqua.blacksmith.api.registry.IInterModRegistry;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.mod.util.Log;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import org.objectweb.asm.Type;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.Callable;

import static net.darkaqua.blacksmith.mod.util.MCInterface.fromStorageHandler;

/**
 * Created by cout970 on 16/01/2016.
 */
public class InterModRegistry implements IInterModRegistry {

    public static final InterModRegistry INSTANCE = new InterModRegistry();
    private static IdentityHashMap<String, List<Function<Capability<?>, Object>>> callbacks;
    private static final Set<Integer> uniqueASMData = new HashSet<>();
    private static List<InterfaceRegistration> registrationData = new LinkedList<>();

    private InterModRegistry() {}

    @Override
    public <T> void registerInterface(Class<T> type, IInterfaceIdentifier.IStorageHandler storage, Callable<? extends T> factory) {
        try {
            factory.call().getClass();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        InterfaceRegistration<T> reg = new InterfaceRegistration<>(type, storage, factory);
        registrationData.add(reg);
        reg.register();
    }

    public List<InterfaceRegistration> getRegistrationData(){
        return new ArrayList<>(registrationData);
    }

    public static void onModConstructs(ASMDataTable data) {

        if (callbacks == null) {
            getCallBacks();
        }
        for (ASMDataTable.ASMData entry : data.getAll(InterfaceIdentifierHolder.class.getName())) {

            int hash = System.identityHashCode(entry);
            if (uniqueASMData.contains(hash)) continue;
            uniqueASMData.add(hash);

            final String targetClass = entry.getClassName();
            final String targetName = entry.getObjectName();
            Type type = (Type) entry.getAnnotationInfo().get("value");
            if (type == null) {
                Log.warn(String.format("Unable to inject interface identifier at %s.%s (Invalid Annotation)", targetClass, targetName));
                return;
            }
            final String capabilityName = type.getInternalName().replace('/', '.').intern();

            List<Function<Capability<?>, Object>> list = callbacks.get(capabilityName);
            if (list == null) {
                list = Lists.newArrayList();
                callbacks.put(capabilityName, list);
            }

            if (entry.getObjectName().indexOf('(') > 0) {
                list.add(input -> {
                    try {
                        for (Method mtd : Class.forName(targetClass).getDeclaredMethods()) {
                            if (targetName.equals(mtd.getName() + Type.getMethodDescriptor(mtd))) {
                                if ((mtd.getModifiers() & Modifier.STATIC) != Modifier.STATIC) {
                                    Log.warn(String.format("Unable to inject interface identifier %s at %s.%s (Non-Static)", capabilityName, targetClass, targetName));
                                    return null;
                                }

                                mtd.setAccessible(true);
                                mtd.invoke(null, MCInterface.fromCapability(input));
                                return null;
                            }
                        }
                        Log.warn(String.format("Unable to inject interface identifier %s at %s.%s (Method Not Found)", capabilityName, targetClass, targetName));
                    } catch (Exception e) {
                        Log.warn(String.format("Unable to inject interface identifier %s at %s.%s", capabilityName, targetClass, targetName));
                        e.printStackTrace();
                    }
                    return null;
                });
            } else {
                list.add(input -> {
                    try {
                        Field field = Class.forName(targetClass).getDeclaredField(targetName);
                        if ((field.getModifiers() & Modifier.STATIC) != Modifier.STATIC) {
                            Log.warn(String.format("Unable to inject interface identifier %s at %s.%s (Non-Static)", capabilityName, targetClass, targetName));
                            return null;
                        }
                        EnumHelper.setFailsafeFieldValue(field, null, MCInterface.fromCapability(input));
                    } catch (Exception e) {
                        Log.warn(String.format("Unable to inject interface identifier %s at %s.%s", capabilityName, targetClass, targetName));
                        e.printStackTrace();
                    }
                    return null;
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void getCallBacks() {
        try {
            Field field = CapabilityManager.class.getDeclaredField("callbacks");
            field.setAccessible(true);
            callbacks = (IdentityHashMap<String, List<Function<Capability<?>, Object>>>) field.get(CapabilityManager.INSTANCE);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void registerDefaultInterfaces() {
        INSTANCE.registerInterface(IInventoryHandler.class, new IInterfaceIdentifier.IStorageHandler() {
            @Override
            public IDataCompound saveData(IInterfaceIdentifier identifier, Object instance, Direction dir) {
                return null;
            }

            @Override
            public void loadData(IInterfaceIdentifier identifier, Object instance, Direction dir, IDataCompound data) {

            }
        }, () -> new SimpleInventoryHandler(0));

    }

    public class InterfaceRegistration<T> {
        private Class<T> type;
        private IInterfaceIdentifier.IStorageHandler storage;
        private Callable<? extends T> factory;

        public InterfaceRegistration(Class<T> type, IInterfaceIdentifier.IStorageHandler storage, Callable<? extends T> factory) {
            this.type = type;
            this.storage = storage;
            this.factory = factory;
        }

        public Class<T> getType() {
            return type;
        }

        public IInterfaceIdentifier.IStorageHandler getStorage() {
            return storage;
        }

        public Callable<? extends T> getFactory() {
            return factory;
        }

        public void register(){
            CapabilityManager.INSTANCE.register(type, fromStorageHandler(storage), factory);
        }
    }
}
