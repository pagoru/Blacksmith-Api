package net.darkaqua.blacksmith.api.render.model.providers.defaults;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.modloader.IModIdentifier;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.IPartIdentifier;
import net.darkaqua.blacksmith.api.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.render.model.RenderTransformation;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;

import java.util.List;
import java.util.function.Function;

/**
 * Created by cout970 on 21/12/2015.
 */
public class PlaneItemModelProvider extends SimpleItemModelProvider {

    protected IStaticModel model;
    protected Function<IModelRegistry, IStaticModel> modelGenerator;

    public PlaneItemModelProvider(IModIdentifier mod, ResourceReference texture) {
        super(reg -> new ItemFlatModel(reg.registerFlatItemModel(mod, texture)));
    }

    public PlaneItemModelProvider(IModIdentifier mod, ResourceReference texture, Function<RenderPlace, RenderTransformation> transform) {
        super(reg -> new ItemFlatModel(reg.registerFlatItemModel(mod, texture), transform));
    }

    public static class ItemFlatModel implements IStaticModel {

        protected IPartIdentifier component;
        protected Function<RenderPlace, RenderTransformation> transform;

        public ItemFlatModel(IPartIdentifier component) {
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

        public ItemFlatModel(IPartIdentifier component, Function<RenderPlace, RenderTransformation> transform) {
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
            return false;
        }
    }
}
