package net.darkaqua.blacksmith.api.render.model.defaults;

import net.darkaqua.blacksmith.api.render.model.IPartIdentifier;
import net.darkaqua.blacksmith.api.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.render.model.RenderTransformation;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 07/12/2015.
 */
public class SimpleModelCompound implements IStaticModel {

    private List<IPartIdentifier> parts;

    public SimpleModelCompound() {
        parts = new LinkedList<>();
    }

    public void addModelPart(IPartIdentifier part) {
        if (part == null) {
            throw new NullPointerException("null model component");
        }
        parts.add(part);
    }

    @Override
    public String getName() {
        return "ModelCompound";
    }

    @Override
    public List<IPartIdentifier> getParts() {
        return parts;
    }

    @Override
    public RenderTransformation getTransformation(RenderPlace place) {

        if (place == RenderPlace.THIRD_PERSON || place == RenderPlace.THIRD_PERSON_LEFT_HAND || place == RenderPlace.THIRD_PERSON_RIGHT_HAND) {
            return new RenderTransformation(new Vect3d(0, 1.5, -2.75).multiply(0.0625F), new Vect3d(10, -45, 170), new Vect3d(0.375f, 0.375f, 0.375f));
        }
        return null;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }

    @Override
    public ResourceReference getParticleTexture() {
        return null;
    }

    @Override
    public boolean needsInventoryRotation() {
        return true;
    }
}
