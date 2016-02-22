package com.cout970.testmod.blocks;

import net.darkaqua.blacksmith.api.common.block.defaults.DefaultBlockDefinition;
import net.darkaqua.blacksmith.api.common.block.blockdata.BlockDataFactory;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttribute;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockDataGenerator;
import net.darkaqua.blacksmith.api.common.block.blockdata.defaults.BlockAttributeValueDirection;

/**
 * Created by cout970 on 15/01/2016.
 */
public class StatefullBlock extends DefaultBlockDefinition {

    public static final IBlockAttribute DIRECTION = BlockDataFactory.createBlockAttribute("direction", BlockAttributeValueDirection.VALUES);

    @Override
    public IBlockDataGenerator getBlockDataGenerator() {
        return BlockDataFactory.createBlockDataGenerator(parent, DIRECTION);
    }
}
