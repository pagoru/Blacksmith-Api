package net.darkaqua.blacksmith.mod.common.intermod;

import net.darkaqua.blacksmith.api.common.intermod.IInterfaceIdentifier;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by cout970 on 16/01/2016.
 */
public class CapabilityWrapper implements IInterfaceIdentifier {

    private Capability<?> capability;

    public CapabilityWrapper(Capability<?> capability) {
        this.capability = capability;
    }

    public Capability<?> getCapability() {
        return capability;
    }

    @Override
    public String getName() {
        return capability.getName();
    }

    @Override
    public Class<?> getInterfaceClass() {
        return capability.getDefaultInstance().getClass();
    }

    @Override
    public Object getDefaultInstance() {
        return capability.getDefaultInstance();
    }

    @Override
    public IStorageHandler getStorageHandler() {
        return MCInterface.toStorageHandler(capability.getStorage());
    }

    @Override
    public boolean matches(IInterfaceIdentifier inter) {
        return inter != null && ((CapabilityWrapper) inter).capability == capability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CapabilityWrapper)) return false;

        CapabilityWrapper that = (CapabilityWrapper) o;

        return !(capability != null ? !capability.equals(that.capability) : that.capability != null);

    }

    @Override
    public int hashCode() {
        return capability != null ? capability.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CapabilityWrapper{" +
                "capability=" + capability +
                "}, capability name=" + getName() +
                ", default instance=" + getDefaultInstance() +
                ", class=" + getInterfaceClass() +
                '}';
    }
}
