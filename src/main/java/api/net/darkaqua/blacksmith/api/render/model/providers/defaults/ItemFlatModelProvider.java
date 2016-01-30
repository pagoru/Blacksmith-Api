package net.darkaqua.blacksmith.api.render.model.providers.defaults;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IModelPartIdentifier;
import net.darkaqua.blacksmith.api.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.render.model.RenderTransformation;
import net.darkaqua.blacksmith.api.render.model.providers.IItemModelProvider;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;

import java.util.List;
import java.util.function.Function;

/**
 * Created by cout970 on 21/12/2015.
 */
public class ItemFlatModelProvider implements IItemModelProvider {

    protected ResourceReference texture;
    protected IStaticModel model;
    protected Function<IModelPartIdentifier, IStaticModel> builder;

    public ItemFlatModelProvider(ResourceReference texture) {
        this.texture = texture;
        builder = ItemFlatModel::new;
    }

    public ItemFlatModelProvider(ResourceReference texture, Function<IModelPartIdentifier, IStaticModel> builder) {
        this.texture = texture;
        this.builder = builder;
    }

    @Override
    public IStaticModel getModelForVariant(IItemStack stack) {
        return model;
    }

    @Override
    public void registerModels(IModelRegistry registry) {
        model = builder.apply(registry.registerFlatItemModel(texture));
    }

    public static class ItemFlatModel implements IStaticModel {

        protected IModelPartIdentifier component;
        protected Function<RenderPlace, RenderTransformation> transform;

        public ItemFlatModel(IModelPartIdentifier component) {
            this.component = component;
            transform = (place) -> {
                if (place == RenderPlace.THIRD_PERSON) {
                    return new RenderTransformation(new Vect3d(0, 1, -3).multiply(1 / 16d), new Vect3d(-90, 0, 0), new Vect3d(0.55, 0.55, 0.55));
                } else if (place == RenderPlace.FIRST_PERSON) {
                    return new RenderTransformation(new Vect3d(0, 4, 2).multiply(1 / 16d), new Vect3d(0, -135, 25), new Vect3d(1.7, 1.7, 1.7));
                }
                return null;
            };
        }

        public ItemFlatModel(IModelPartIdentifier component, Function<RenderPlace, RenderTransformation> transform) {
            this.component = component;
            this.transform = transform;
        }

        @Override
        public String getName() {
            return "FlatItemModel";
        }

        @Override
        public RenderTransformation getTransformation(RenderPlace place) {
            return transform.apply(place);
        }

        @Override
        public List<IModelPartIdentifier> getParts() {
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
            return false;
        }
    }
}
