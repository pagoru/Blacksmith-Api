package net.darkaqua.blacksmith.mod.render;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.darkaqua.blacksmith.api.render.model.IRenderTransformationProvider;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.render.model.RenderTransformation;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.*;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by cout970 on 21/12/2015.
 */
public class BS_ItemLayerModel extends ItemLayerModel {

    private IRenderTransformationProvider provider;

    public BS_ItemLayerModel(ImmutableList<ResourceLocation> textures, IRenderTransformationProvider provider) {
        super(textures);
        this.provider = provider;
    }

    public IFlexibleBakedModel bake(IModelState state, final VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {

        ImmutableMap.Builder<ItemCameraTransforms.TransformType, TRSRTransformation> builder = new ImmutableMap.Builder<>();

        builder.put(ItemCameraTransforms.TransformType.THIRD_PERSON, toTransformVec(provider.getTransformation(RenderPlace.THIRD_PERSON)));
        builder.put(ItemCameraTransforms.TransformType.FIRST_PERSON, toTransformVec(provider.getTransformation(RenderPlace.FIRST_PERSON)));
        builder.put(ItemCameraTransforms.TransformType.HEAD, toTransformVec(provider.getTransformation(RenderPlace.HEAD)));
        builder.put(ItemCameraTransforms.TransformType.GUI, toTransformVec(provider.getTransformation(RenderPlace.GUI)));
        builder.put(ItemCameraTransforms.TransformType.GROUND, toTransformVec(provider.getTransformation(RenderPlace.GROUND)));
        builder.put(ItemCameraTransforms.TransformType.FIXED, toTransformVec(provider.getTransformation(RenderPlace.FIXED)));
        ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> map = builder.build();

        return new IPerspectiveAwareModel.MapWrapper(super.bake(state, format, bakedTextureGetter), map);
    }

    private TRSRTransformation toTransformVec(RenderTransformation transformation) {
        if (transformation == null) {
            return TRSRTransformation.identity();
        }

        Vect3d vec1 = transformation.getRotation();
        Vect3d vec2 = transformation.getTranslation();
        Vect3d vec3 = transformation.getScale();

        return new TRSRTransformation(new ItemTransformVec3f(
                new Vector3f((float) vec1.getX(), (float) vec1.getY(), (float) vec1.getZ()),
                new Vector3f((float) vec2.getX(), (float) vec2.getY(), (float) vec2.getZ()),
                new Vector3f((float) vec3.getX(), (float) vec3.getY(), (float) vec3.getZ())));
    }
}
