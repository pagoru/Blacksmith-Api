package net.darkaqua.blacksmith.api.common.util;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.intermod.IInterfaceIdentifier;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.item.IItem;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntity;

/**
 * Created by cout970 on 13/11/2015.
 */
public abstract class ObjectScanner {

    protected static ObjectScanner INSTANCE;

    public static <T> T findInItem(IItem toScan, Class<T> clazz) {
        return INSTANCE.find(toScan, clazz);
    }

    public static <T> T findInItemStack(IItemStack toScan, Class<T> clazz) {
        return INSTANCE.find(toScan, clazz);
    }

    public static <T> T findInBlock(IBlock toScan, Class<T> clazz) {
        return INSTANCE.find(toScan, clazz);
    }

    public static <T> T findInBlockVariant(IBlockData toScan, Class<T> clazz) {
        return INSTANCE.find(toScan, clazz);
    }

    public static <T> T findInTileEntity(ITileEntity toScan, Class<T> clazz) {
        return INSTANCE.find(toScan, clazz);
    }

    public static <T> T findInItem(IItem toScan, IInterfaceIdentifier<T> id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    public static <T> T findInItemStack(IItemStack toScan, IInterfaceIdentifier<T> id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    public static <T> T findInBlock(IBlock toScan, IInterfaceIdentifier<T> id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    public static <T> T findInBlockVariant(IBlockData toScan, IInterfaceIdentifier<T> id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    public static <T> T findInTileEntity(ITileEntity toScan, IInterfaceIdentifier<T> id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    protected abstract <T> T find(Object toScan, Class<T> clazz);

    protected abstract <T> T findInterface(Object toScan, IInterfaceIdentifier<T> id, Direction dir);
}
