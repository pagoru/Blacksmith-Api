package net.darkaqua.blacksmith.api.render;

import net.darkaqua.blacksmith.api.block.blockstate.IIBlockState;
import net.darkaqua.blacksmith.api.render.model.IBlockStateModelMapper;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;

import java.util.List;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface IBlockRenderHandler {

    //TODO
    ITileEntityModel getTileEntityModel(ITileEntity tileEntity);

    //TODO test
    List<IBlockStateModelMapper> getBlockModelsForState(IIBlockState state);
}
