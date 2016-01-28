package net.darkaqua.blacksmith.api.render.model.providers.defaults;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IModelPart;
import net.darkaqua.blacksmith.api.render.model.IModelPartIdentifier;
import net.darkaqua.blacksmith.api.render.model.IStaticModel;

/**
 * Created by cout970 on 29/12/2015.
 */
public class SimpleItemBlockModelProvider extends EmptyBlockModelProvider {

    protected IModelPart part;
    protected IStaticModel model;

    public SimpleItemBlockModelProvider(IModelPart part) {
        this.part = part;
    }

    @Override
    public IStaticModel getModelForItemBlock(IItemStack stack) {
        return model;
    }

    @Override
    public void registerModels(IModelRegistry registry) {
        model = fromModelPart(registry.registerModelPart(part));
    }

    public IStaticModel fromModelPart(IModelPartIdentifier id) {
        return new SimpleItemModelProvider.ItemModel(id);
    }
}
