package net.darkaqua.blacksmith.api.render.model.generated;

import net.darkaqua.blacksmith.api.render.ResourceReference;

import java.util.List;

/**
 * Created by cout970 on 07/12/2015.
 */
public interface IGenModel {

    List<ResourceReference> getTextures();

    List<IGenQuad> getQuads();
}
