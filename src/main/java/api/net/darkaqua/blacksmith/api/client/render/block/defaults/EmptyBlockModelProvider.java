package net.darkaqua.blacksmith.api.client.render.block.defaults;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.client.render.model.defaults.EmptyModel;
import net.darkaqua.blacksmith.api.client.render.block.IBlockModelProvider;
import net.darkaqua.blacksmith.api.client.render.model.IStaticModel;

/**
 * Created by cout970 on 28/12/2015.
 */
public class EmptyBlockModelProvider implements IBlockModelProvider {

    public static final IStaticModel model = new EmptyModel();

    @Override
    public IStaticModel getModelForBlockData(IBlockData variant) {
        return model;
    }

    @Override
    public IStaticModel getModelForItemBlock(IItemStack stack) {
        return model;
    }

    @Override
    public void reloadModels(IModelRegistry registry) {}
}
