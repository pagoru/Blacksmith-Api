package net.darkaqua.blacksmith.api.client.render.model.defaults;

import net.darkaqua.blacksmith.api.client.render.model.*;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cout970 on 21/12/2015.
 */
public class EmptyModel implements IStaticModel {

    @Override
    public RenderTransformation getTransformation(RenderPlace place) {
        return null;
    }

    @Override
    public IModelProperties getProperties() {
        return new IModelProperties() {
            @Override
            public boolean useAmbientOcclusion() {
                return false;
            }

            @Override
            public ResourceReference getParticleTexture() {
                return null;
            }

            @Override
            public boolean needsInventoryRotation() {
                return false;
            }
        };
    }

    @Override
    public List<IPartIdentifier> getParts() {
        return new ArrayList<>(0);
    }
}
