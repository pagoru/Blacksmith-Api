package net.darkaqua.blacksmith.api.client.render.item;

import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.client.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;

/**
 * Created by cout970 on 16/12/2015.
 */
@Implementable
public interface IItemModelProvider {

    IStaticModel getModelForVariant(IItemStack stack);

    void reloadModels(IModelRegistry registry);
}
