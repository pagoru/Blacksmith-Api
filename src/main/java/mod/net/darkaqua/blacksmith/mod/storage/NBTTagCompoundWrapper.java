package net.darkaqua.blacksmith.mod.storage;

import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.storage.IDataElement;
import net.darkaqua.blacksmith.api.storage.IDataList;
import net.darkaqua.blacksmith.mod.exceptions.BlacksmithInternalException;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Created by cout970 on 16/12/2015.
 */
public class NBTTagCompoundWrapper implements IDataCompound {

    private NBTTagCompound nbt;

    public NBTTagCompoundWrapper(NBTTagCompound nbt) {
        this.nbt = nbt;
    }

    public NBTTagCompound getNBTTagCompound() {
        return nbt;
    }

    @Override
    public void removeKey(String name) {
        nbt.removeTag(name);
    }

    @Override
    public boolean containsKey(String name) {
        return nbt.hasKey(name);
    }

    @Override
    public void setInteger(String name, int value) {
        nbt.setInteger(name, value);
    }

    @Override
    public void setLong(String name, long value) {
        nbt.setLong(name, value);
    }

    @Override
    public void setFloat(String name, float value) {
        nbt.setFloat(name, value);
    }

    @Override
    public void setDouble(String name, double value) {
        nbt.setDouble(name, value);
    }

    @Override
    public void setString(String name, String value) {
        nbt.setString(name, value);
    }

    @Override
    public void setIntegerArray(String name, int[] value) {
        nbt.setIntArray(name, value);
    }

    @Override
    public void setByte(String name, byte value) {
        nbt.setByte(name, value);
    }

    @Override
    public void setByteArray(String name, byte[] value) {
        nbt.setByteArray(name, value);
    }

    @Override
    public void setBoolean(String name, boolean value) {
        nbt.setBoolean(name, value);
    }

    @Override
    public void setDataElement(String name, IDataElement value) {
        if (value instanceof NBTBaseWrapper) {
            nbt.setTag(name, ((NBTBaseWrapper) value).getNBTBase());
        } else if (value instanceof NBTTagCompoundWrapper) {
            nbt.setTag(name, ((NBTTagCompoundWrapper) value).getNBTTagCompound());
        } else if (value instanceof NBTTagListWrapper) {
            nbt.setTag(name, ((NBTTagListWrapper) value).getNBTTagList());
        } else if (value != null) {
            throw new BlacksmithInternalException("Invalid IDataElement implementation: " + value + ", with class: " + value.getClass());
        }
    }

    @Override
    public int getInteger(String name) {
        return nbt.getInteger(name);
    }

    @Override
    public long getLong(String name) {
        return nbt.getLong(name);
    }

    @Override
    public float getFloat(String name) {
        return nbt.getFloat(name);
    }

    @Override
    public double getDouble(String name) {
        return nbt.getDouble(name);
    }

    @Override
    public String getString(String name) {
        return nbt.getString(name);
    }

    @Override
    public int[] getIntegerArray(String name) {
        return nbt.getIntArray(name);
    }

    @Override
    public byte getByte(String name) {
        return nbt.getByte(name);
    }

    @Override
    public byte[] getByteArray(String name) {
        return nbt.getByteArray(name);
    }

    @Override
    public boolean getBoolean(String name) {
        return nbt.getBoolean(name);
    }

    @Override
    public IDataElement getDataElement(String name) {
        NBTBase base = nbt.getTag(name);
        if (base != null) {
            if (base instanceof NBTTagCompound) {
                return new NBTTagCompoundWrapper((NBTTagCompound) base);
            } else if (base instanceof NBTTagList) {
                return new NBTTagListWrapper((NBTTagList) base);
            } else {
                return new NBTBaseWrapper(base);
            }
        }
        return null;
    }

    @Override
    public IDataList getDataList(String name) {
        NBTBase base = nbt.getTag(name);
        if (base instanceof NBTTagList) {
            return new NBTTagListWrapper((NBTTagList) base);
        }
        return new NBTTagListWrapper(new NBTTagList());
    }

    @Override
    public IDataCompound getDataCompound(String name) {
        NBTBase base = nbt.getTag(name);
        if (base instanceof NBTTagCompound) {
            return new NBTTagCompoundWrapper((NBTTagCompound) base);
        }
        return new NBTTagCompoundWrapper(new NBTTagCompound());
    }

    @Override
    public byte getID() {
        return nbt.getId();
    }

    @Override
    public IDataCompound copy() {
        return new NBTTagCompoundWrapper((NBTTagCompound) nbt.copy());
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof NBTTagCompoundWrapper)) return false;

        NBTTagCompoundWrapper that = (NBTTagCompoundWrapper) o;

        return !(nbt != null ? !nbt.equals(that.nbt) : that.nbt != null);

    }

    @Override
    public String toString() {
        return "IDataCompound{" +
                "data=" + nbt +
                '}';
    }

    @Override
    public int hashCode() {
        return nbt != null ? nbt.hashCode() : 0;
    }

    @Override
    public Object getInternalNBTBase() {
        return nbt;
    }

    @Override
    public Object getInternalNBTCompound() {
        return nbt;
    }
}
