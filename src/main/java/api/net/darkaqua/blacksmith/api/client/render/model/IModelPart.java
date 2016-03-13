package net.darkaqua.blacksmith.api.client.render.model;

import net.darkaqua.blacksmith.api.common.util.ResourceReference;
import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;

import java.util.List;

/**
 * Created by cout970 on 27/12/2015.
 */
@Implementable
public interface IModelPart {

    List<IModelQuad> getQuads();

    ResourceReference getTexture();

    boolean useShade();
}
