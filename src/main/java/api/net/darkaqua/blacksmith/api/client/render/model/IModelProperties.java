package net.darkaqua.blacksmith.api.client.render.model;

import net.darkaqua.blacksmith.api.common.util.ResourceReference;

/**
 * Created by cout970 on 11/03/2016.
 */
public interface IModelProperties {

    boolean useAmbientOcclusion();

    ResourceReference getParticleTexture();

    boolean needsInventoryRotation();
}
