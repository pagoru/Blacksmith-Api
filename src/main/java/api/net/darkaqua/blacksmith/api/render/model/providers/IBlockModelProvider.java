package net.darkaqua.blacksmith.api.render.model.providers;

import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.util.annotations.Implementable;

/**
 * Created by cout970 on 14/12/2015.
 */
@Implementable
public interface IBlockModelProvider {

    IStaticModel getModelForBlockData(IBlockData variant);

    IStaticModel getModelForItemBlock(IItemStack stack);

    void reloadModels(IModelRegistry registry);
}
