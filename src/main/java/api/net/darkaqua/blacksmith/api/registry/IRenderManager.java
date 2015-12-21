package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.util.ResourceReference;

/**
 * Created by cout970 on 20/12/2015.
 */
public interface IRenderManager {

    void renderItemStack(IItemStack stack, RenderPlace place);

    void renderModel(IModelIdentifier model);

    void bindTexture(ResourceReference resourceReference);

    ResourceReference getBlockTextureAtlas();
}
