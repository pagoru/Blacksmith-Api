package net.darkaqua.blacksmith.mod.render.model;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IItemModelProvider;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;

/**
 * Created by cout970 on 27/12/2015.
 */
public class ItemBlockModelProvider implements IItemModelProvider {

    protected IRenderModel model;

    public ItemBlockModelProvider(IRenderModel model) {
        this.model = model;
    }

    @Override
    public IRenderModel getModelForVariant(IItemStack stack) {
        return model;
    }

    @Override
    public void registerModels(IModelRegistry registry) {}
}
