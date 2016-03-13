package net.darkaqua.blacksmith.api.client.render.block.defaults;

import net.darkaqua.blacksmith.api.client.render.block.IBlockModelProvider;
import net.darkaqua.blacksmith.api.client.render.model.IModelFactory;
import net.darkaqua.blacksmith.api.client.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.registry.IModelRegistry;

/**
 * Created by cout970 on 13/03/2016.
 */
public class UniqueModelProvider implements IBlockModelProvider {

    protected IStaticModel model;
    protected IModelFactory factory;

    public UniqueModelProvider(IModelFactory factory) {
        this.factory = factory;
    }

    @Override
    public IStaticModel getModelForBlockData(IBlockData variant) {
        return model;
    }

    @Override
    public IStaticModel getModelForItemBlock(IItemStack stack) {
        return model;
    }

    @Override
    public void reloadModels(IModelRegistry registry) {
        model = factory.createModels(registry).values().stream().findFirst().get();
    }
}
