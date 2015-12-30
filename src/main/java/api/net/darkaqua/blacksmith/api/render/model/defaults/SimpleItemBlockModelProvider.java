package net.darkaqua.blacksmith.api.render.model.defaults;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IModelPart;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;

/**
 * Created by cout970 on 29/12/2015.
 */
public class SimpleItemBlockModelProvider extends EmptyBlockModelProvider {

    protected IModelPart part;
    protected IRenderModel model;

    public SimpleItemBlockModelProvider(IModelPart part) {
        this.part = part;
    }

    @Override
    public IRenderModel getModelForItemBlock(IItemStack stack) {
        return model;
    }

    @Override
    public void registerModels(IModelRegistry registry) {
        model = new SimpleItemModelProvider.ItemModel(registry.registerModelPart(part));
    }
}
