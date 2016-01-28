package net.darkaqua.blacksmith.api.render.model.providers;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.util.annotations.Implementable;

/**
 * Created by cout970 on 16/12/2015.
 */
@Implementable
public interface IItemModelProvider {

    IStaticModel getModelForVariant(IItemStack stack);

    void registerModels(IModelRegistry registry);
}
