package net.darkaqua.blacksmith.api.render;

import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.world.IIBlockAccess;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface IBlockRenderHandler {


    IBlockModel getBlockModel(IIBlockAccess state);

    ITileEntityModel getTileEntityModel(ITileEntity tileEntity);

}
