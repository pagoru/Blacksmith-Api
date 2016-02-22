package net.darkaqua.blacksmith.api.client.render.block;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.client.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;

/**
 * Created by cout970 on 14/12/2015.
 */
@Implementable
public interface IBlockModelProvider {

    IStaticModel getModelForBlockData(IBlockData variant);

    IStaticModel getModelForItemBlock(IItemStack stack);

    void reloadModels(IModelRegistry registry);
}
