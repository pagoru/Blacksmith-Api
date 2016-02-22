package net.darkaqua.blacksmith.api.common.block;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;
import net.darkaqua.blacksmith.api.common.world.IWorld;

/**
 * Created by cout970 on 15/11/2015.
 */
@Implementable
public interface IBlockContainerDefinition extends IBlockDefinition {

    ITileEntityDefinition createTileEntity(IWorld world, IBlockData state);
}
