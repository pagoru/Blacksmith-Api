package net.darkaqua.blacksmith.api.render.model.providers.defaults;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IStaticModel;

import java.util.function.Function;

/**
 * Created by cout970 on 29/12/2015.
 */
public class SimpleItemBlockModelProvider extends EmptyBlockModelProvider {

    protected IStaticModel itemModel;
    protected Function<IModelRegistry, IStaticModel> modelGenerator;

    public SimpleItemBlockModelProvider(Function<IModelRegistry, IStaticModel> modelGenerator) {
        this.modelGenerator = modelGenerator;
    }

    @Override
    public IStaticModel getModelForItemBlock(IItemStack stack) {
        return itemModel;
    }

    @Override
    public void reloadModels(IModelRegistry registry) {
        itemModel = modelGenerator.apply(registry);
    }
}
