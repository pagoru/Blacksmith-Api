package net.darkaqua.blacksmith.api.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.IBlock;

/**
 * Created by cout970 on 15/01/2016.
 */
public abstract class BlockDataFactory {

    protected static BlockDataFactory INSTANCE;

    public static IBlockAttribute createBlockAttribute(String name, IBlockAttributeValue... attr){
        return INSTANCE.newBlockAttribute(name, attr);
    }

    public static IBlockDataHandler createBlockDataHandler(IBlock block, IBlockAttribute... attr) {
        return INSTANCE.newBlockDataHandler(block, attr);
    }

    protected abstract IBlockDataHandler newBlockDataHandler(IBlock block, IBlockAttribute[] attr);

    protected abstract IBlockAttribute newBlockAttribute(String name, IBlockAttributeValue[] attr);
}