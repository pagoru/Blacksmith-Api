package net.darkaqua.blacksmith.api.render.model.defaults;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.StaticAccess;
import net.darkaqua.blacksmith.api.render.model.*;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;

import java.util.List;

/**
 * Created by cout970 on 21/12/2015.
 */
public class ItemFlatModelProvider implements IItemModelProvider {

    protected IModelIdentifier identifier;

    public ItemFlatModelProvider(ResourceReference texture){
        identifier = StaticAccess.GAME.getRenderRegistry().registerFlatItemModel(texture, new RenderTransformationProvider());
    }

    public ItemFlatModelProvider(ResourceReference texture, IRenderTransformationProvider provider){
        identifier = StaticAccess.GAME.getRenderRegistry().registerFlatItemModel(texture, provider);
    }

    @Override
    public IModelIdentifier getModelForVariant(IItemStack stack) {
        return identifier;
    }

    @Override
    public void bindModelIdentifier(IRenderModel model, IModelIdentifier identifier) {}

    @Override
    public List<IRenderModel> getAllModels() {
        return Lists.newArrayList();
    }

    @Override
    public List<IModelIdentifier> getExtraModels() {
        return Lists.newArrayList(identifier);
    }

    public static class RenderTransformationProvider implements IRenderTransformationProvider{

        @Override
        public RenderTransformation getTransformation(RenderPlace place) {
            if (place == RenderPlace.THIRD_PERSON){
                return new RenderTransformation(new Vect3d(0, 1, -3).multiply(1/16d), new Vect3d(-90, 0, 0), new Vect3d(0.55, 0.55, 0.55));
            }else if (place == RenderPlace.FIRST_PERSON){
                return new RenderTransformation(new Vect3d(0, 4, 2).multiply(1/16d), new Vect3d(0, -135, 25), new Vect3d(1.7, 1.7, 1.7));
            }
            return null;
        }
    }
}
