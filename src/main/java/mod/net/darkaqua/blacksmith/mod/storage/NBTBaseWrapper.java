package net.darkaqua.blacksmith.mod.storage;

import net.darkaqua.blacksmith.api.storage.IDataElement;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by cout970 on 16/12/2015.
 */
public class NBTBaseWrapper implements IDataElement {

    private NBTBase nbt;

    public NBTBaseWrapper(NBTBase nbt){
        this.nbt = nbt;
    }

    public NBTBase getNBTBase(){
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

    public NBTTagCompound asNBTTagCompound(){
        if (nbt instanceof NBTTagCompound){
            return (NBTTagCompound) nbt;
        }
        throw new ClassCastException();
    }
}
