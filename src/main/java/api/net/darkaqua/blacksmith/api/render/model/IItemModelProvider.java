package net.darkaqua.blacksmith.api.render.model;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;

/**
 * Created by cout970 on 16/12/2015.
 */
public interface IItemModelProvider {

    IRenderModel getModelForVariant(IItemStack stack);

    void registerModels(IModelRegistry registry);
}
