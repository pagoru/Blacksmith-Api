package net.darkaqua.blacksmith.api.render.model.defaults;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.*;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;

import java.util.List;

/**
 * Created by cout970 on 14/12/2015.
 */
public class SimpleBlockModelProvider implements IBlockModelProvider{

    protected IModelPartIdentifier identifier;
    protected IModelPart component;
    protected IRenderModel model;

    public SimpleBlockModelProvider(IModelPart component) {
        this.component = component;
    }

    @Override
    public IRenderModel getModelForVariant(IBlockVariant variant) {
        if (model == null){
            model = new BlockModel(identifier);
        }
        return model;
    }

    @Override
    public void registerModels(IModelRegistry registry) {
        identifier = registry.registerModelPart(component);
    }


    public static class BlockModel implements IRenderModel{

        protected IModelPartIdentifier component;

        public BlockModel(IModelPartIdentifier component) {
            this.component = component;
        }

        @Override
        public String getName() {
            return "BlockModel";
        }

        @Override
        public RenderTransformation getTransformation(RenderPlace place) {
            if (place == RenderPlace.THIRD_PERSON || place == RenderPlace.THIRD_PERSON_LEFT_HAND || place == RenderPlace.THIRD_PERSON_RIGHT_HAND) {
                return new RenderTransformation(new Vect3d(0, 1.5, -2.75).multiply(0.0625F), new Vect3d(10.0, -45.0, 170.0), new Vect3d(0.375f, 0.375f, 0.375f));
            }
            return null;
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
            return true;
        }
    }
}
