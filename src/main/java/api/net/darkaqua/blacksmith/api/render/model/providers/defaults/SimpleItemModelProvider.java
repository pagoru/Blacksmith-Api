package net.darkaqua.blacksmith.api.render.model.providers.defaults;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.modloader.IModIdentifier;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.*;
import net.darkaqua.blacksmith.api.render.model.providers.IItemModelProvider;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;

import java.util.List;
import java.util.function.Function;

/**
 * Created by cout970 on 19/12/2015.
 */
public class SimpleItemModelProvider implements IItemModelProvider {

    protected IStaticModel model;
    protected Function<IModelRegistry, IStaticModel> modelGenerator;

    public SimpleItemModelProvider(Function<IModelRegistry, IStaticModel> modelGenerator) {
        this.modelGenerator = modelGenerator;
    }

    public SimpleItemModelProvider(IModIdentifier mod, IModelPart part) {
        this(reg -> new ItemModel(reg.registerModelPart(mod, part)));
    }

    @Override
    public IStaticModel getModelForVariant(IItemStack stack) {
        return model;
    }

    @Override
    public void reloadModels(IModelRegistry registry) {
        model = modelGenerator.apply(registry);
    }

    public static class ItemModel implements IStaticModel {

        protected IPartIdentifier component;
        protected Function<RenderPlace, RenderTransformation> transform;
        protected boolean rotation = true;

        public ItemModel(IPartIdentifier component) {
            this.component = component;
            transform = place -> {
                if (place == RenderPlace.THIRD_PERSON) {
                    return new RenderTransformation(new Vect3d(0, 1, -3).multiply(1 / 16d), new Vect3d(-90, 0, 0), new Vect3d(0.55, 0.55, 0.55));
                } else if (place == RenderPlace.FIRST_PERSON) {
                    return new RenderTransformation(new Vect3d(0, 4, 2).multiply(1 / 16d), new Vect3d(0, -135, 25), new Vect3d(1.7, 1.7, 1.7));
                }
                return null;
            };
        }

        public ItemModel(IPartIdentifier component, Function<RenderPlace, RenderTransformation> transform) {
            this.component = component;
            this.transform = transform;
        }

        public ItemModel(IPartIdentifier component, Function<RenderPlace, RenderTransformation> transform, boolean rotation) {
            this.component = component;
            this.transform = transform;
            this.rotation = rotation;
        }

        @Override
        public String getName() {
            return "ItemModel";
        }

        @Override
        public RenderTransformation getTransformation(RenderPlace place) {

            return transform.apply(place);
        }

        @Override
        public List<IPartIdentifier> getParts() {
            return Lists.newArrayList(component);
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
            return rotation;
        }
    }

}
