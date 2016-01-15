package net.darkaqua.blacksmith.mod.storage;

import net.darkaqua.blacksmith.api.storage.IDataElement;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by cout970 on 16/12/2015.
 */
public class NBTBaseWrapper implements IDataElement {

    private NBTBase nbt;

    public NBTBaseWrapper(NBTBase nbt) {
        this.nbt = nbt;
    }

    public NBTBase getNBTBase() {
        return nbt;
    }

    @Override
    public byte getID() {
        return nbt.getId();
    }

    @Override
    public IDataElement copy() {
        return new NBTBaseWrapper(nbt.copy());
    }

    @Override
    public Object getInternalNBTBase() {
        return nbt;
    }

    public NBTTagCompound asNBTTagCompound() {
        if (nbt instanceof NBTTagCompound) {
            return (NBTTagCompound) nbt;
        }
        throw new ClassCastException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NBTBaseWrapper)) return false;

        NBTBaseWrapper that = (NBTBaseWrapper) o;

        return !(nbt != null ? !nbt.equals(that.nbt) : that.nbt != null);

    }

    @Override
    public String toString() {
        return "IDataElement{" +
                "data=" + nbt +
                '}';
    }

    @Override
    public int hashCode() {
        return nbt != null ? nbt.hashCode() : 0;
    }
}
