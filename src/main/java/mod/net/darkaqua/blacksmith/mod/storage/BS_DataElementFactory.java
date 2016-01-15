package net.darkaqua.blacksmith.mod.storage;

import net.darkaqua.blacksmith.api.storage.DataElementFactory;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.storage.IDataList;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Created by cout970 on 21/12/2015.
 */
public class BS_DataElementFactory extends DataElementFactory {

    private BS_DataElementFactory() {
    }

    public static void init() {
        INSTANCE = new BS_DataElementFactory();
    }

    @Override
    protected IDataCompound newDataCompound() {
        return MCInterface.fromNBTCompound(new NBTTagCompound());
    }

    @Override
    protected IDataList newDataList() {
        return MCInterface.fromNBTList(new NBTTagList());
    }
}
