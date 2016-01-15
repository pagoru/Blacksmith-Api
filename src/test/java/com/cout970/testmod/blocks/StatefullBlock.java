package com.cout970.testmod.blocks;

import net.darkaqua.blacksmith.api.block.defaults.DefaultBlockDefinition;
import net.darkaqua.blacksmith.api.block.variants.BlockDataFactory;
import net.darkaqua.blacksmith.api.block.variants.IBlockAttribute;
import net.darkaqua.blacksmith.api.block.variants.IBlockDataGenerator;
import net.darkaqua.blacksmith.api.block.variants.defaults.BlockAttributeValueDirection;

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
