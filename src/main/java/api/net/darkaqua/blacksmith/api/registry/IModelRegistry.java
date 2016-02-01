package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.render.model.IModelPart;
import net.darkaqua.blacksmith.api.render.model.IPartIdentifier;
import net.darkaqua.blacksmith.api.util.ResourceReference;

/**
 * Created by cout970 on 22/12/2015.
 */
public interface IModelRegistry {

    IPartIdentifier registerModelPart(IModelPart model);

    IPartIdentifier registerFlatItemModel(ResourceReference... texture);
}
