package net.darkaqua.blacksmith.api.common.registry;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.block.IBlockDefinition;

import java.util.List;

/**
 * Created by cout970 on 08/11/2015.
 */
public interface IBlockRegistry {

    IBlock registerBlockDefinition(IBlockDefinition definition, String identifier);

    List<IBlock> getRegisteredBlocks();

    List<IBlockDefinition> getRegisteredBlockDefinitions();

    IBlock findBlock(String domain, String name);

    String getBlockDomain(IBlock block);

    String getBlockName(IBlock block);
}
