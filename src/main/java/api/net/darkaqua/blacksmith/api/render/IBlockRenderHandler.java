package net.darkaqua.blacksmith.api.render;

import net.darkaqua.blacksmith.api.block.blockstate.IIBlockState;
import net.darkaqua.blacksmith.api.render.model.IBlockModel;
import net.darkaqua.blacksmith.api.render.model.IBlockStateModel;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;

import java.util.List;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface IBlockRenderHandler {

    /**
     * Associates a IIBlockState id with a list of IBlockStateModel
     * where the IBlockStateModel represents the models for every blockState and they transformations
     */
    List<IBlockStateModel> getBlockStateModels();

    /**
     * Gets the block model with the modelName obtained from IBlockStateModel.getModelName();
     */
    IBlockModel getModel(String modelName);

    /**
     * Gets the IIBlockState liked with the list of IBlockStateModel obtained from getBlockStateModels
     */
    IIBlockState getBlockState(String id);

    //TODO
    ITileEntityModel getTileEntityModel(ITileEntity tileEntity);
}
