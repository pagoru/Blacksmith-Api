package net.darkaqua.blacksmith.mod.common.intermod;

import net.darkaqua.blacksmith.api.common.intermod.IInterfaceIdentifier;
import net.darkaqua.blacksmith.api.common.storage.IDataCompound;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by cout970 on 16/01/2016.
 */
public class StorageHandlerWrapper implements Capability.IStorage {

    private IInterfaceIdentifier.IStorageHandler handler;

    public StorageHandlerWrapper(IInterfaceIdentifier.IStorageHandler handler) {
        this.handler = handler;
    }

    public IInterfaceIdentifier.IStorageHandler getHandler() {
        return handler;
    }

    @Override
    public NBTBase writeNBT(Capability capability, Object instance, EnumFacing side) {
        IDataCompound data = handler.saveData(MCInterface.fromCapability(capability), instance, MCInterface.fromEnumFacing(side));
        return MCInterface.toNBTCompound(data);
    }

    @Override
    public void readNBT(Capability capability, Object instance, EnumFacing side, NBTBase nbt) {
        IDataCompound data;
        if (nbt instanceof NBTTagCompound){
            data = MCInterface.fromNBTCompound((NBTTagCompound) nbt);
        }else{
            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag("data", nbt);
            data = MCInterface.fromNBTCompound(tag);
        }
        handler.loadData(MCInterface.fromCapability(capability), instance, MCInterface.fromEnumFacing(side), data);
    }
}
