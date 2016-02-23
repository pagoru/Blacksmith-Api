package com.cout970.testmod.blocks;

import net.darkaqua.blacksmith.api.common.block.blockdata.BlockDataFactory;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockDataHandler;
import net.darkaqua.blacksmith.api.common.block.defaults.DefaultBlockDefinition;

/**
 * Created by cout970 on 15/01/2016.
 */
public class StatefullBlock extends DefaultBlockDefinition {

    @Override
    public IBlockDataHandler getBlockDataGenerator() {
        return BlockDataFactory.createBlockDataHandler(parent, BlockDataFactory.ATTRIBUTE_ALL_DIRECTIONS);
    }
}
