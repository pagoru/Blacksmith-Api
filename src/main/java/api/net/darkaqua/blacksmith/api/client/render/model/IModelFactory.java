package net.darkaqua.blacksmith.api.client.render.model;

import net.darkaqua.blacksmith.api.common.registry.IModelRegistry;

import java.util.Map;

/**
 * Created by cout970 on 13/03/2016.
 */
public interface IModelFactory {

    Map<String, IStaticModel> createModels(IModelRegistry registry);
}
