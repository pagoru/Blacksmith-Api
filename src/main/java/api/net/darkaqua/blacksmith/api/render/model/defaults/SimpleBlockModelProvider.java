package net.darkaqua.blacksmith.api.render.model.defaults;

import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IBlockModelProvider;
import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cout970 on 14/12/2015.
 */
public class SimpleBlockModelProvider implements IBlockModelProvider{

    private IRenderModel model;
    private IModelIdentifier identifier;

    public SimpleBlockModelProvider(IRenderModel model){
        this.model = model;
    }

    @Override
    public IModelIdentifier getModelForVariant(IBlockVariant variant) {
        return identifier;
    }

    @Override
    public void registerModels(IModelRegistry registry) {
        identifier = registry.registerRenderModel(model);
    }

    @Override
    public List<IModelIdentifier> getValidModels() {
        return Arrays.asList(identifier);
    }
}
