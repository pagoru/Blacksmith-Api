package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.render.model.IModelPart;
import net.darkaqua.blacksmith.api.render.model.IModelPartIdentifier;
import net.darkaqua.blacksmith.api.util.ResourceReference;

/**
 * Created by cout970 on 22/12/2015.
 */
public interface IModelRegistry {

    IModelPartIdentifier registerModelPart(IModelPart model);

    IModelPartIdentifier registerFlatItemModel(ResourceReference... texture);
}
