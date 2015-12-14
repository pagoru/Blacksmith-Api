package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.render.model.IBlockModelProvider;

/**
 * Created by cout970 on 07/12/2015.
 */
public interface IRenderRegistry {

    boolean registerBlockModelProvider(IBlockDefinition def, IBlockModelProvider provider);
}