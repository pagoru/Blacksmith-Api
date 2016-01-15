package net.darkaqua.blacksmith.api.render.model;

import net.darkaqua.blacksmith.api.block.variants.IBlockData;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.util.annotations.Implementable;

/**
 * Created by cout970 on 14/12/2015.
 */
@Implementable
public interface IBlockModelProvider {

    IRenderModel getModelForVariant(IBlockData variant);

    IRenderModel getModelForItemBlock(IItemStack stack);

    void registerModels(IModelRegistry registry);
}
