package net.darkaqua.blacksmith.api.render.model;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;

import java.util.List;

/**
 * Created by cout970 on 16/12/2015.
 */
public interface IItemModelProvider {

    IModelIdentifier getModelForVariant(IItemStack stack);

    void registerModels(IModelRegistry registry);

    List<IModelIdentifier> getValidModels();
}
