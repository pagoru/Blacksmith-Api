package net.darkaqua.blacksmith.api.client.render.model.defaults;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.client.render.model.*;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by cout970 on 13/03/2016.
 */
public class SimpleBlockModel implements IStaticModel {

    protected List<IPartIdentifier> components;
    protected Function<RenderPlace, RenderTransformation> transform;

    public SimpleBlockModel(IPartIdentifier... component) {
        this(Lists.newArrayList(component));
    }

    public SimpleBlockModel(IPartIdentifier component, Function<RenderPlace, RenderTransformation> transform) {
        components = new ArrayList<>(1);
        components.add(component);
        this.transform = transform;
    }

    public SimpleBlockModel(List<IPartIdentifier> component) {
        components = new ArrayList<>(component.size());
        components.addAll(component);
        transform = place -> {
            if (place == RenderPlace.THIRD_PERSON || place == RenderPlace.THIRD_PERSON_LEFT_HAND || place == RenderPlace.THIRD_PERSON_RIGHT_HAND) {
                return new RenderTransformation(new Vect3d(0, 1.5, -2.75).multiply(0.0625F), new Vect3d(10.0, -45.0, 170.0), new Vect3d(0.375f, 0.375f, 0.375f));
            }
            return null;
        };
    }

    @Override
    public RenderTransformation getTransformation(RenderPlace place) {
        return transform.apply(place);
    }

    @Override
    public IModelProperties getProperties() {
        return new IModelProperties() {
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
        };
    }

    @Override
    public List<IPartIdentifier> getParts() {
        return components;
    }
}