package net.darkaqua.blacksmith.api.render.model.defaults;

import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IBlockModelProvider;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;

/**
 * Created by cout970 on 28/12/2015.
 */
public class EmptyBlockModelProvider implements IBlockModelProvider {

    public static final IRenderModel model = new EmptyModel();
    @Override
    public IRenderModel getModelForVariant(IBlockVariant variant) {
        return model;
    }

    @Override
    public void registerModels(IModelRegistry registry) {}
}
