package net.darkaqua.blacksmith.api.render.model.defaults;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.render.model.IItemModelProvider;
import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;

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
    public void bindModelIdentifier(IRenderModel model, IModelIdentifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public List<IRenderModel> getAllModels() {
        List<IRenderModel> list = Lists.newArrayList();
        list.add(model);
        return list;
    }

    @Override
    public List<IModelIdentifier> getExtraModels() {
        return Lists.newArrayList();
    }
}
