package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockDefinition;

import java.util.List;

/**
 * Created by cout970 on 08/11/2015.
 */
public interface IBlockRegistry {

    IBlock registerBlockDefinition(IBlockDefinition definition, String identifier);

    List<IBlock> getRegisteredBlocks();

    List<IBlockDefinition> getRegisteredBlockDefinitions();
}
