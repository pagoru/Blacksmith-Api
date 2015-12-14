package net.darkaqua.blacksmith.api.render.model;

import net.darkaqua.blacksmith.api.util.ResourceReference;

import javax.vecmath.Matrix4f;
import java.util.List;

/**
 * Created by cout970 on 07/12/2015.
 */
public interface IRenderModel {

    String getName();

    List<ResourceReference> getTextures();

    List<IModelPart> getSubParts();

    Matrix4f getTransformationMatrix(RenderPlace place);
}
