package net.darkaqua.blacksmith.api.render.model.default_models;

import net.darkaqua.blacksmith.api.render.model.*;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;

import java.util.*;

/**
 * Created by cout970 on 07/12/2015.
 */
public class SimpleRenderModel implements IRenderModel {

    private List<IModelPart> parts;
    private Set<ResourceReference> textures;

    public SimpleRenderModel() {
        parts = new LinkedList<>();
        textures = new HashSet<>();
    }

    public void addModelPart(IModelPart part) {
        if (part == null) {
            throw new NullPointerException("null model part");
        }
        parts.add(part);
        for (IModelQuad quad : part.getQuads()) {
            textures.add(quad.getTexture());
        }
    }

    @Override
    public String getName() {
        return "BasicModel";
    }

    @Override
    public List<ResourceReference> getTextures() {
        return new ArrayList<>(textures);
    }

    @Override
    public List<IModelPart> getSubParts() {
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
        return false;
    }
}
