package net.darkaqua.blacksmith.api.render.model;

import net.darkaqua.blacksmith.api.inventory.IItemStack;

import java.util.List;

/**
 * Created by cout970 on 16/12/2015.
 */
public interface IItemModelProvider {

    IModelIdentifier getModelForVariant(IItemStack stack);

    void bindModelIdentifier(IRenderModel model, IModelIdentifier identifier);

    List<IRenderModel> getAllModels();

    List<IModelIdentifier> getExtraModels();
}
