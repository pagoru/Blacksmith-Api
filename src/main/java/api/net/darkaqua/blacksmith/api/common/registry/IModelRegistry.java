package net.darkaqua.blacksmith.api.common.registry;

import net.darkaqua.blacksmith.api.common.modloader.IModIdentifier;
import net.darkaqua.blacksmith.api.client.render.model.IModelPart;
import net.darkaqua.blacksmith.api.client.render.model.IPartIdentifier;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;

/**
 * Created by cout970 on 22/12/2015.
 */
public interface IModelRegistry {

    IPartIdentifier registerModelPart(IModIdentifier mod, IModelPart model);

    IPartIdentifier registerFlatItemModel(IModIdentifier mod, ResourceReference... texture);
}
