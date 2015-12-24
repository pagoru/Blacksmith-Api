package net.darkaqua.blacksmith.api.render.model.defaults;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IItemModelProvider;
import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cout970 on 19/12/2015.
 */
public class SimpleItemModelProvider implements IItemModelProvider {

    protected IRenderModel model;
    protected IModelIdentifier identifier;

    public SimpleItemModelProvider(IRenderModel model) {
        this.model = model;
    }

    @Override
    public IModelIdentifier getModelForVariant(IItemStack stack) {
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
