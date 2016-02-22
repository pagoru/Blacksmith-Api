package net.darkaqua.blacksmith.mod.client.render.model;

import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.client.render.block.IBlockModelProvider;
import net.darkaqua.blacksmith.api.client.render.item.IItemModelProvider;
import net.darkaqua.blacksmith.api.client.render.model.IStaticModel;

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
