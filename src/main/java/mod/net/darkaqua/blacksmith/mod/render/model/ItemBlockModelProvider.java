package net.darkaqua.blacksmith.mod.render.model;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.providers.IBlockModelProvider;
import net.darkaqua.blacksmith.api.render.model.providers.IItemModelProvider;
import net.darkaqua.blacksmith.api.render.model.IStaticModel;

/**
 * Created by cout970 on 27/12/2015.
 */
public class ItemBlockModelProvider implements IItemModelProvider {

    protected IBlockModelProvider provider;

    public ItemBlockModelProvider(IBlockModelProvider provider) {
        this.provider = provider;
    }

    @Override
    public IStaticModel getModelForVariant(IItemStack stack) {
        return provider.getModelForItemBlock(stack);
    }

    @Override
    public void reloadModels(IModelRegistry registry) {
    }
}
