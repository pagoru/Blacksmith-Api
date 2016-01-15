package net.darkaqua.blacksmith.mod.render.model;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IBlockModelProvider;
import net.darkaqua.blacksmith.api.render.model.IItemModelProvider;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;

/**
 * Created by cout970 on 27/12/2015.
 */
public class ItemBlockModelProvider implements IItemModelProvider {

    protected IBlockModelProvider provider;

    public ItemBlockModelProvider(IBlockModelProvider provider) {
        this.provider = provider;
    }

    @Override
    public IRenderModel getModelForVariant(IItemStack stack) {
        return provider.getModelForItemBlock(stack);
    }

    @Override
    public void registerModels(IModelRegistry registry) {
    }
}
