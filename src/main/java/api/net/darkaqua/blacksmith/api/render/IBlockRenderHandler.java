package net.darkaqua.blacksmith.api.render;

import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface IBlockRenderHandler {

    IBlockModel getBlockModel(IIBlockState state);

    ITileEntityModel getTileEntityModel(ITileEntity tileEntity);

}
