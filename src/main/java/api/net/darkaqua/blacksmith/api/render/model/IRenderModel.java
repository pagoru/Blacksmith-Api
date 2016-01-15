package net.darkaqua.blacksmith.api.render.model;

import net.darkaqua.blacksmith.api.util.ResourceReference;

import java.util.List;

/**
 * Created by cout970 on 07/12/2015.
 */
public interface IRenderModel {

    String getName();

    RenderTransformation getTransformation(RenderPlace place);

    List<IModelPartIdentifier> getParts();

    boolean useAmbientOcclusion();

    ResourceReference getParticleTexture();

    boolean needsInventoryRotation();
}
