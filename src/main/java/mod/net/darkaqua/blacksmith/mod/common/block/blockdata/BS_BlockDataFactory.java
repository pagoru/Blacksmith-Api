package net.darkaqua.blacksmith.mod.common.block.blockdata;

import com.google.common.collect.Sets;
import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.block.blockdata.BlockDataFactory;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttribute;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttributeValue;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockDataHandler;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
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
    protected IBlockDataHandler newBlockDataHandler(IBlock block, IBlockAttribute[] attr) {
        IProperty[] list = new IProperty[attr.length];
        for(int i = 0; i < attr.length; i++){
            list[i] = MCInterface.fromBlockAttribute(attr[i]);
        }
        return MCInterface.toBlockDataHandler(new BlockState(MCInterface.toBlock(block), list));
    }

    @Override
    protected IBlockAttribute newBlockAttribute(String name, IBlockAttributeValue[] attr) {
        return new BlockAttribute<>(name, Sets.newHashSet(attr));
    }
}
