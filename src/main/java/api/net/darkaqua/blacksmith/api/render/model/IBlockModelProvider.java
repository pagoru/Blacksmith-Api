package net.darkaqua.blacksmith.api.render.model;

import net.darkaqua.blacksmith.api.block.IBlockVariant;

import java.util.List;

/**
 * Created by cout970 on 14/12/2015.
 */
public interface IBlockModelProvider {

    IModelIdentifier getModelForVariant(IBlockVariant variant);

    void bindModelIdentifier(IRenderModel model, IModelIdentifier identifier);

    List<IRenderModel> getAllModels();
}
