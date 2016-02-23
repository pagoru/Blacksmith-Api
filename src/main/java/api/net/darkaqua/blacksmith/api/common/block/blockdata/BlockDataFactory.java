package net.darkaqua.blacksmith.api.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.util.Direction;

/**
 * Created by cout970 on 15/01/2016.
 */
public abstract class BlockDataFactory {

    public static IBlockAttribute<Direction> ATTRIBUTE_ALL_DIRECTIONS;
    public static IBlockAttribute<Direction> ATTRIBUTE_HORIZONTAL_DIRECTIONS;
    protected static BlockDataFactory INSTANCE;

    public static IBlockDataHandler createBlockDataHandler(IBlock block, IBlockAttribute... attr) {
        return INSTANCE.newBlockDataHandler(block, attr);
    }

    public static <T extends Comparable<T>> IBlockAttribute<T> createBlockAttribute(String name, Class<T> clazz, T[] attr){
        return INSTANCE.newBlockAttribute(name, clazz, attr);
    }

    protected abstract IBlockDataHandler newBlockDataHandler(IBlock block, IBlockAttribute[] attr);

    protected abstract <T extends Comparable<T>>  IBlockAttribute<T> newBlockAttribute(String name, Class<T> clazz, T[] attr);
}