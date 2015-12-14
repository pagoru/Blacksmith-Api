package net.darkaqua.blacksmith.api.render.model.default_models;

import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.render.model.IBlockModelProvider;
import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;

import java.util.ArrayList;
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
    public void bindModelIdentifier(IRenderModel model, IModelIdentifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public List<IRenderModel> getAllModels() {
        List<IRenderModel> list = new ArrayList<>();
        list.add(model);
        return list;
    }
}
