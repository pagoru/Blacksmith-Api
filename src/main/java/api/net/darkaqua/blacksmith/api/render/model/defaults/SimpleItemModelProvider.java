package net.darkaqua.blacksmith.api.render.model.defaults;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.render.model.*;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;

import java.util.List;

/**
 * Created by cout970 on 19/12/2015.
 */
public class SimpleItemModelProvider implements IItemModelProvider {

    protected IModelPart component;
    protected IModelPartIdentifier identifier;
    protected IRenderModel model;

    public SimpleItemModelProvider(IModelPart component) {
        this.component = component;
    }

    @Override
    public IRenderModel getModelForVariant(IItemStack stack) {
        if (model == null) {
            model = createRenderModel();
        }
        return model;
    }

    public IRenderModel createRenderModel() {
        return new ItemModel(identifier);
    }

    @Override
    public void registerModels(IModelRegistry registry) {
        identifier = registry.registerModelPart(component);
    }


    public static class ItemModel implements IRenderModel {

        protected IModelPartIdentifier component;

        public ItemModel(IModelPartIdentifier component) {
            this.component = component;
        }

        @Override
        public String getName() {
            return "ItemModel";
        }

        @Override
        public RenderTransformation getTransformation(RenderPlace place) {
            if (place == RenderPlace.THIRD_PERSON) {
                return new RenderTransformation(new Vect3d(0, 1, -3).multiply(1 / 16d), new Vect3d(-90, 0, 0), new Vect3d(0.55, 0.55, 0.55));
            } else if (place == RenderPlace.FIRST_PERSON) {
                return new RenderTransformation(new Vect3d(0, 4, 2).multiply(1 / 16d), new Vect3d(0, -135, 25), new Vect3d(1.7, 1.7, 1.7));
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
