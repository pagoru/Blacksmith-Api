package net.darkaqua.blacksmith.api.render.model.providers.defaults;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.modloader.IModIdentifier;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.*;
import net.darkaqua.blacksmith.api.render.model.providers.IBlockModelProvider;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;

import java.util.List;
import java.util.function.Function;

/**
 * Created by cout970 on 14/12/2015.
 */
public class SimpleBlockModelProvider implements IBlockModelProvider {

    protected IStaticModel model;
    protected Function<IModelRegistry, IStaticModel> modelGenerator;

    public SimpleBlockModelProvider(Function<IModelRegistry, IStaticModel> modelGenerator) {
        this.modelGenerator = modelGenerator;
    }

    public SimpleBlockModelProvider(IModIdentifier mod, IModelPart part) {
        this(reg -> new BlockModel(reg.registerModelPart(mod, part)));
    }

    @Override
    public IStaticModel getModelForBlockData(IBlockData variant) {
        return model;
    }

    @Override
    public IStaticModel getModelForItemBlock(IItemStack stack) {
        return model;
    }

    @Override
    public void reloadModels(IModelRegistry registry) {
        model = modelGenerator.apply(registry);
    }

    public static class BlockModel implements IStaticModel {

        protected IPartIdentifier component;
        protected Function<RenderPlace, RenderTransformation> transform;

        public BlockModel(IPartIdentifier component) {
            this.component = component;
            transform = place -> {
                if (place == RenderPlace.THIRD_PERSON || place == RenderPlace.THIRD_PERSON_LEFT_HAND || place == RenderPlace.THIRD_PERSON_RIGHT_HAND) {
                    return new RenderTransformation(new Vect3d(0, 1.5, -2.75).multiply(0.0625F), new Vect3d(10.0, -45.0, 170.0), new Vect3d(0.375f, 0.375f, 0.375f));
                }
                return null;
            };
        }

        public BlockModel(IPartIdentifier component, Function<RenderPlace, RenderTransformation> transform){
            this(component);
            this.transform = transform;
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
            return true;
        }
    }
}
