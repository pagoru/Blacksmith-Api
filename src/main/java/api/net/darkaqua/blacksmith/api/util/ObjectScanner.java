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
