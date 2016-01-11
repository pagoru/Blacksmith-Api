package net.darkaqua.blacksmith.api.util;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;

/**
 * Created by cout970 on 13/11/2015.
 */
public abstract class ObjectScanner {

    protected static ObjectScanner INSTANCE;

    public static <T> T findInItem(IItem toScan, Class<T> clazz) {
        return findByClass(toScan, clazz);
    }

    public static <T> T findInItemStack(IItemStack toScan, Class<T> clazz) {
        return findByClass(toScan.getItem(), clazz);
    }

    public static <T> T findInBlock(IBlock toScan, Class<T> clazz) {
        return findByClass(toScan, clazz);
    }

    public static <T> T findInBlockVariant(IBlockVariant toScan, Class<T> clazz) {
        return findByClass(toScan, clazz);
    }


    public static <T> T findInTileEntity(ITileEntity toScan, Class<T> clazz) {
        return findByClass(toScan, clazz);
    }

    private static <T> T findByClass(Object toScan, Class<T> clazz) {
        return INSTANCE.find(toScan, clazz);
    }

    protected abstract <T> T find(Object toScan, Class<T> clazz);
}
