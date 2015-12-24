package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;
import net.darkaqua.blacksmith.api.render.model.IRenderTransformationProvider;
import net.darkaqua.blacksmith.api.util.ResourceReference;

/**
 * Created by cout970 on 22/12/2015.
 */
public interface IModelRegistry {

    IModelIdentifier registerRenderModel(IRenderModel model);

    IModelIdentifier registerFlatItemModel(ResourceReference texture, IRenderTransformationProvider provider);
}
