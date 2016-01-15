package net.darkaqua.blacksmith.mod.storage;

import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.storage.IDataList;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.nbt.NBTTagList;

/**
 * Created by cout970 on 21/12/2015.
 */
public class NBTTagListWrapper implements IDataList {

    private NBTTagList nbt;

    public NBTTagListWrapper(NBTTagList nbt) {
        this.nbt = nbt;
    }

    public NBTTagList getNBTTagList() {
        return nbt;
    }

    @Override
    public int getSize() {
        return nbt.tagCount();
    }

    @Override
    public IDataCompound getDataCompound(int index) {
        return MCInterface.fromNBTCompound(nbt.getCompoundTagAt(index));
    }

    @Override
    public void addDataCompound(IDataCompound data) {
        nbt.appendTag(MCInterface.toNBTCompound(data));
    }

    @Override
    public void setDataCompound(IDataCompound data, int index) {
        nbt.set(index, MCInterface.toNBTCompound(data));
    }

    @Override
    public void removeDataCompound(int index) {
        nbt.removeTag(index);
    }

    @Override
    public Object getInternalNBTList() {
        return nbt;
    }

    @Override
    public byte getID() {
        return nbt.getId();
    }

    @Override
    public IDataList copy() {
        return MCInterface.fromNBTList((NBTTagList) nbt.copy());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NBTTagListWrapper)) return false;

        NBTTagListWrapper that = (NBTTagListWrapper) o;

        return !(nbt != null ? !nbt.equals(that.nbt) : that.nbt != null);

    }

    @Override
    public int hashCode() {
        return nbt != null ? nbt.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "IDataList{" +
                "data=" + nbt +
                '}';
    }

    @Override
    public Object getInternalNBTBase() {
        return nbt;
    }
}
