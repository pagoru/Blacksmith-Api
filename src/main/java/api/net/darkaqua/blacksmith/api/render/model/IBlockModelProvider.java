package net.darkaqua.blacksmith.api.render.model;

import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;

import java.util.List;

/**
 * Created by cout970 on 14/12/2015.
 */
public interface IBlockModelProvider {

    IModelIdentifier getModelForVariant(IBlockVariant variant);

    void registerModels(IModelRegistry registry);

    List<IModelIdentifier> getValidModels();
}
