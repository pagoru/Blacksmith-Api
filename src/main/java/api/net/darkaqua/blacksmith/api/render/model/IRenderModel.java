package net.darkaqua.blacksmith.api.render.model;

import net.darkaqua.blacksmith.api.util.ResourceReference;

import java.util.List;

/**
 * Created by cout970 on 07/12/2015.
 */
public interface IRenderModel extends IRenderTransformationProvider{

    String getName();

    List<ResourceReference> getTextures();

    List<IModelPart> getSubParts();

    boolean useAmbientOcclusion();
}
