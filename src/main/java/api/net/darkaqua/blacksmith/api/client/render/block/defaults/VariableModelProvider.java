package net.darkaqua.blacksmith.api.client.render.block.defaults;

import net.darkaqua.blacksmith.api.client.render.block.IBlockModelProvider;
import net.darkaqua.blacksmith.api.client.render.block.IBlockModelSelector;
import net.darkaqua.blacksmith.api.client.render.model.IModelFactory;
import net.darkaqua.blacksmith.api.client.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.registry.IModelRegistry;

import java.util.Map;

/**
 * Created by cout970 on 13/03/2016.
 */
public class VariableModelProvider implements IBlockModelProvider {

    protected Map<String, IStaticModel> models;
    protected IModelFactory factory;
    protected IBlockModelSelector selector;

    public VariableModelProvider(IModelFactory factory, IBlockModelSelector selector) {
        this.factory = factory;
        this.selector = selector;
    }

    @Override
    public IStaticModel getModelForBlockData(IBlockData data) {
        return selector.select(data, models);
    }

    @Override
    public IStaticModel getModelForItemBlock(IItemStack stack) {
        return selector.select(stack, models);
    }

    @Override
    public void reloadModels(IModelRegistry registry) {
        models = factory.createModels(registry);
    }
}
