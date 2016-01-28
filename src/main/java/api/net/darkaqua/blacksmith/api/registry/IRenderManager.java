package net.darkaqua.blacksmith.api.registry;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.render.model.IModelPartIdentifier;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;

import java.util.Collection;

/**
 * Created by cout970 on 20/12/2015.
 */
public interface IRenderManager {

    void renderItemStack(IItemStack stack, Vect3d pos, RenderPlace place);

    void bindTexture(ResourceReference resourceReference);

    void renderModelParts(Collection<IModelPartIdentifier> parts, WorldRef ref, Vect3d offset);

    default void renderModelParts(WorldRef ref, Vect3d offset, IModelPartIdentifier... parts){
        renderModelParts(Lists.newArrayList(parts), ref, offset);
    }
}
