package net.darkaqua.blacksmith.mod.block.blockstate.newest;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.variants.BlockDataFactory;
import net.darkaqua.blacksmith.api.block.variants.IBlockAttribute;
import net.darkaqua.blacksmith.api.block.variants.IBlockAttributeValue;
import net.darkaqua.blacksmith.api.block.variants.IBlockDataGenerator;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BS_BlockDataFactory extends BlockDataFactory{

    private BS_BlockDataFactory(){
    }

    public static void init(){
        INSTANCE = new BS_BlockDataFactory();
    }

    @Override
    protected IBlockDataGenerator newBlockDataGenerator(IBlock block, IBlockAttribute[] attr) {
        IProperty[] list = new IProperty[attr.length];
        for(int i = 0; i < attr.length; i++){
            list[i] = MCInterface.fromBlockAttribute(attr[i]);
        }
        return MCInterface.toBlockDataGenerator(new BlockState(MCInterface.toBlock(block), list));
    }

    @Override
    protected IBlockAttribute newBlockAttribute(String name, IBlockAttributeValue[] attr) {
        return new BlockAttribute(name, Lists.newArrayList(attr));
    }
}
