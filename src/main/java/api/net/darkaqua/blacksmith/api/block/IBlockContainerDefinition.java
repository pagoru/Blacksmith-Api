package net.darkaqua.blacksmith.api.block;

import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.world.IWorld;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface IBlockContainerDefinition extends IBlockDefinition {

    ITileEntityDefinition createTileEntity(IWorld world, IIBlockState state);
}
