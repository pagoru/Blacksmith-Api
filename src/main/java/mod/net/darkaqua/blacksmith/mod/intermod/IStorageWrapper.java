package net.darkaqua.blacksmith.mod.intermod;

import net.darkaqua.blacksmith.api.intermod.IInterfaceIdentifier;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by cout970 on 16/01/2016.
 */
public class IStorageWrapper implements IInterfaceIdentifier.IStorageHandler {

    private Capability.IStorage<Object> storage;

    @SuppressWarnings("unchecked")
    public IStorageWrapper(Capability.IStorage<?> storage) {
        this.storage = (Capability.IStorage<Object>) storage;
    }

    public Capability.IStorage<?> getStorage() {
        return storage;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IDataCompound saveData(IInterfaceIdentifier identifier, Object instance, Direction dir) {
        if (!identifier.getInterfaceClass().isAssignableFrom(instance.getClass())){
            throw new IllegalArgumentException("The instance: '"+instance+"' is not a valid type of "+identifier);
        }
        Capability<?> cap = MCInterface.toCapability(identifier);
        NBTBase base = storage.writeNBT((Capability<Object>) cap, instance, MCInterface.toEnumFacing(dir));
        if (base instanceof NBTTagCompound){
            return MCInterface.fromNBTCompound((NBTTagCompound) base);
        }
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("data", base);
        return MCInterface.fromNBTCompound(nbt);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void loadData(IInterfaceIdentifier identifier, Object instance, Direction dir, IDataCompound data) {
        if (!identifier.getInterfaceClass().isAssignableFrom(instance.getClass())){
            throw new IllegalArgumentException("The instance: '"+instance+"' is not a valid type of "+identifier);
        }
        Capability<?> cap = MCInterface.toCapability(identifier);
        storage.readNBT((Capability<Object>) cap, instance, MCInterface.toEnumFacing(dir), MCInterface.toNBTCompound(data));
    }
}
