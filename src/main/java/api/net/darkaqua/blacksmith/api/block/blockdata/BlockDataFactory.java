package net.darkaqua.blacksmith.api.block.blockdata;

import net.darkaqua.blacksmith.api.block.IBlock;

/**
 * Created by cout970 on 15/01/2016.
 */
public abstract class BlockDataFactory {

    protected static BlockDataFactory INSTANCE;

    public static IBlockAttribute createBlockAttribute(String name, IBlockAttributeValue... attr){
        return INSTANCE.newBlockAttribute(name, attr);
    }

    public static IBlockDataGenerator createBlockDataGenerator(IBlock block, IBlockAttribute... attr) {
        return INSTANCE.newBlockDataGenerator(block, attr);
    }

    protected abstract IBlockDataGenerator newBlockDataGenerator(IBlock block, IBlockAttribute[] attr);

    protected abstract IBlockAttribute newBlockAttribute(String name, IBlockAttributeValue[] attr);
}