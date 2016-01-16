package net.darkaqua.blacksmith.api.util;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.intermod.IInterfaceIdentifier;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;

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

    public static Object findInItem(IItem toScan, IInterfaceIdentifier id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    public static Object findInItemStack(IItemStack toScan, IInterfaceIdentifier id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    public static Object findInBlock(IBlock toScan, IInterfaceIdentifier id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    public static Object findInBlockVariant(IBlockData toScan, IInterfaceIdentifier id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    public static Object findInTileEntity(ITileEntity toScan, IInterfaceIdentifier id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    protected abstract <T> T find(Object toScan, Class<T> clazz);

    protected abstract Object findInterface(Object toScan, IInterfaceIdentifier id, Direction dir);
}
