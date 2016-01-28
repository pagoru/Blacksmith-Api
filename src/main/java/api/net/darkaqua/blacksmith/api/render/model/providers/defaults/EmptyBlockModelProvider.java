package net.darkaqua.blacksmith.api.render.model.providers.defaults;

import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.defaults.EmptyModel;
import net.darkaqua.blacksmith.api.render.model.providers.IBlockModelProvider;
import net.darkaqua.blacksmith.api.render.model.IStaticModel;

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
    public void registerModels(IModelRegistry registry) {
    }
}
