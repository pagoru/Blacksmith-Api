package net.darkaqua.blacksmith.api.render.model.defaults;

import net.darkaqua.blacksmith.api.render.model.IModelPartIdentifier;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.render.model.RenderTransformation;
import net.darkaqua.blacksmith.api.util.ResourceReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cout970 on 21/12/2015.
 */
public class EmptyModel implements IRenderModel {

    @Override
    public String getName() {
        return "empty";
    }

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

    @Override
    public RenderTransformation getTransformation(RenderPlace place) {
        return null;
    }

    @Override
    public List<IModelPartIdentifier> getParts() {
        return new ArrayList<>(0);
    }
}
