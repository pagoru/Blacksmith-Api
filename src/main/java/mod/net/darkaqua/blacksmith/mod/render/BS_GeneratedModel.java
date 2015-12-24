package net.darkaqua.blacksmith.mod.render;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;
import net.darkaqua.blacksmith.mod.util.Log;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.*;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by cout970 on 07/12/2015.
 */
public class BS_GeneratedModel implements IModel {

    private IRenderModel model;

    public BS_GeneratedModel(IRenderModel model){
        this.model = model;
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return Lists.newArrayList();
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        LinkedList<ResourceLocation> list = new LinkedList();
        for (ResourceReference loc : model.getTextures()){
            list.add(new ResourceLocation(loc.getDomain(), loc.getPath()));
        }
        return list;
    }

    @Override
    public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        Log.debug("BAKING MODEL ===================================================================================================");
        IFlexibleBakedModel model = BS_BakedModel.of(this, state, format, bakedTextureGetter);
        Log.debug(model);
        Log.debug("BAKING MODEL ===================================================================================================");
        return model;
    }

    @Override
    public IModelState getDefaultState() {
        return null;
    }

    public IRenderModel getGenModel() {
        return model;
    }
}
