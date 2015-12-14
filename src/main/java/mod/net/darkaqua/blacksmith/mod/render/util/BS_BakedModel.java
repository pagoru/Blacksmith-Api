package net.darkaqua.blacksmith.mod.render.util;

import com.google.common.base.Function;
import net.darkaqua.blacksmith.api.render.model.IModelPart;
import net.darkaqua.blacksmith.api.render.model.IModelQuad;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.mod.render.BS_GeneratedModel;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector2d;
import javax.vecmath.Vector3d;
import java.util.*;

/**
 * Created by cout970 on 07/12/2015.
 */
public class BS_BakedModel implements IFlexibleBakedModel, IPerspectiveAwareModel{

    private EnumMap<EnumFacing, List<BakedQuad>> quads;
    private List<BakedQuad> generalQuads;
    private VertexFormat vertexFormat;
    private BS_GeneratedModel model;
    private Map<ResourceLocation, TextureAtlasSprite> textures;
    private TextureAtlasSprite particles;

    private BS_BakedModel(BS_GeneratedModel model, IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter){
        quads = new EnumMap<>(EnumFacing.class);
        for(EnumFacing e : EnumFacing.values()){
            quads.put(e, new ArrayList<BakedQuad>(1));
        }
        generalQuads = new ArrayList<>();
        this.vertexFormat = format;
        this.model = model;
        textures = new HashMap<>();
        for(ResourceLocation loc : model.getTextures()){
            if (particles == null){
                particles = bakedTextureGetter.apply(loc);
            }
            textures.put(loc, bakedTextureGetter.apply(loc));
        }
        if (textures.isEmpty()){
            particles = bakedTextureGetter.apply(new ResourceLocation("missingno"));
            textures.put(new ResourceLocation("missingno"), particles);
        }
    }

    public static IFlexibleBakedModel of(BS_GeneratedModel model, IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        BS_BakedModel baked = new BS_BakedModel(model, state, format, bakedTextureGetter);
        baked.generateQuads(model.getGenModel());
        return baked;
    }

    private void generateQuads(IRenderModel model) {
        try {
            for(IModelPart part : model.getSubParts()) {
                for (IModelQuad s : part.getQuads()) {
                    UnBakedQuad uQuad = new UnBakedQuad(MCInterface.toEnumFacing(s.getNormal()));

                    for (IModelQuad.QuadVertex v : IModelQuad.QuadVertex.values()) {
                        Vector2d in_uv = MCInterface.toVector2d(s.getUV(v));

                        if (in_uv == null)
                            throw new IllegalStateException("Invalid UV creating a IGenQuad(" + s + ") for IGenModel(" + model + ")");

                        TextureAtlasSprite sprite = textures.get(MCInterface.toResourceLocation(s.getTexture()));
                        if (sprite == null)
                            throw new IllegalStateException("Some IGenQuad(" + s + ") uses a texture(" + s.getTexture() + ") that is not provided by IGenModel(" + model + ")");

                        Vector2d uv = new Vector2d(sprite.getInterpolatedU(in_uv.getX() * 16), sprite.getInterpolatedV(in_uv.getY() * 16));
                        Vector3d vertex = MCInterface.toVector3d(s.getVertex(v));

                        if (vertex == null)
                            throw new IllegalStateException("Invalid UV creating a IGenQuad(" + s + ") for IGenModel(" + model + ")");

                        uQuad.addVertex(vertex, uv);
                    }
                    if (s.getNormal() == null) {
                        generalQuads.add(uQuad.bake());
                    } else {
                        quads.get(MCInterface.toEnumFacing(s.getNormal())).add(uQuad.bake());
                    }
                }
            }
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing side) {
        return quads.get(side);
    }

    @Override
    public List<BakedQuad> getGeneralQuads() {
        return generalQuads;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getTexture() {
        return particles;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }

    @Override
    public VertexFormat getFormat() {
        return vertexFormat;
    }

    @Override
    public Pair<IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        Matrix4f mat =  model.getGenModel().getTransformationMatrix(getRenderPlace(cameraTransformType));
        if(mat == null) {
            mat = new Matrix4f();
            mat.setIdentity();
        }
        return new ImmutablePair<IBakedModel, Matrix4f>(this, mat);
    }

    private RenderPlace getRenderPlace(ItemCameraTransforms.TransformType cameraTransformType) {
        switch (cameraTransformType){
            case NONE:
                return RenderPlace.NONE;
            case THIRD_PERSON:
                return RenderPlace.THIRD_PERSON;
            case FIRST_PERSON:
                return RenderPlace.FIRST_PERSON;
            case HEAD:
                return RenderPlace.HEAD;
            case GUI:
                return RenderPlace.GUI;
            default: return RenderPlace.NONE;
        }
    }
}
