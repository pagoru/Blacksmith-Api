package net.darkaqua.blacksmith.mod.render.util;

import com.google.common.base.Function;
import net.darkaqua.blacksmith.api.render.model.*;
import net.darkaqua.blacksmith.api.util.Vect2d;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.mod.render.BS_GeneratedModel;
import net.darkaqua.blacksmith.mod.util.Log;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

import javax.vecmath.Matrix4f;
import java.nio.FloatBuffer;
import java.util.*;

/**
 * Created by cout970 on 07/12/2015.
 */
public class BS_BakedModel implements IFlexibleBakedModel, IPerspectiveAwareModel {

    private EnumMap<EnumFacing, List<BakedQuad>> quads;
    private List<BakedQuad> generalQuads;
    private VertexFormat vertexFormat;
    private BS_GeneratedModel model;
    private Map<ResourceLocation, TextureAtlasSprite> textures;
    private TextureAtlasSprite particles;
    private ItemCameraTransforms trans;
    private EnumMap<ItemCameraTransforms.TransformType, Matrix4f> transformMap;

    private BS_BakedModel(BS_GeneratedModel model, IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        quads = new EnumMap<>(EnumFacing.class);
        for (EnumFacing e : EnumFacing.values()) {
            quads.put(e, new ArrayList<BakedQuad>(1));
        }
        generalQuads = new ArrayList<>();
        this.vertexFormat = format;
        this.model = model;
        textures = new HashMap<>();
        for (ResourceLocation loc : model.getTextures()) {
            if (particles == null) {
                particles = bakedTextureGetter.apply(loc);
            }
            textures.put(loc, bakedTextureGetter.apply(loc));
        }
        if (textures.isEmpty()) {
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
            for (IModelPart part : model.getSubParts()) {
                for (IModelQuad s : part.getQuads()) {
                    UnBakedQuad uQuad = new UnBakedQuad(MCInterface.toEnumFacing(s.getNormal()));

                    for (IModelQuad.QuadVertex v : IModelQuad.QuadVertex.values()) {
                        Vect2d in_uv = s.getUV(v);

                        if (in_uv == null)
                            throw new IllegalStateException("Invalid UV creating a IGenQuad(" + s + ") for IGenModel(" + model + ")");

                        TextureAtlasSprite sprite = textures.get(MCInterface.toResourceLocation(s.getTexture()));
                        if (sprite == null)
                            throw new IllegalStateException("Some IGenQuad(" + s + ") uses a texture(" + s.getTexture() + ") that is not provided by IGenModel(" + model + ")");

                        Vect2d uv = new Vect2d(sprite.getInterpolatedU(in_uv.getX() * 16), sprite.getInterpolatedV(in_uv.getY() * 16));
                        Vect3d vertex = s.getVertex(v);

                        if (vertex == null)
                            throw new IllegalStateException("Invalid UV creating a IGenQuad(" + s + ") for IGenModel(" + model + ")");

                        uQuad.addVertex(vertex, uv);
                    }

                    uQuad.setShade(s.useShade());
                    if (s.getNormal() == null) {
                        generalQuads.add(uQuad.bake());
                    } else {
                        quads.get(MCInterface.toEnumFacing(s.getNormal())).add(uQuad.bake());
                    }
                }
            }
        } catch (IllegalStateException e) {
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
        return model.getGenModel().useAmbientOcclusion();
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
        if (trans == null) {
            ItemTransformVec3f thirdPerson = toTransformVec(model.getGenModel().getTransformation(RenderPlace.THIRD_PERSON));
            ItemTransformVec3f firstPerson = toTransformVec(model.getGenModel().getTransformation(RenderPlace.FIRST_PERSON));
            ItemTransformVec3f head = toTransformVec(model.getGenModel().getTransformation(RenderPlace.HEAD));
            ItemTransformVec3f gui = toTransformVec(model.getGenModel().getTransformation(RenderPlace.GUI));
            ItemTransformVec3f ground = toTransformVec(model.getGenModel().getTransformation(RenderPlace.GROUND));
            ItemTransformVec3f fixed = toTransformVec(model.getGenModel().getTransformation(RenderPlace.FIXED));
            trans = new ItemCameraTransforms(thirdPerson, firstPerson, head, gui, ground, fixed);
        }
        return trans;
    }

    private ItemTransformVec3f toTransformVec(RenderTransformation transformation) {
        if (transformation == null) {
            return ItemTransformVec3f.DEFAULT;
        }

        Vect3d vec1 = transformation.getRotation();
        Vect3d vec2 = transformation.getTranslation();
        Vect3d vec3 = transformation.getScale();

        return new ItemTransformVec3f(
                new Vector3f((float) vec1.getX(), (float) vec1.getY(), (float) vec1.getZ()),
                new Vector3f((float) vec2.getX(), (float) vec2.getY(), (float) vec2.getZ()),
                new Vector3f((float) vec3.getX(), (float) vec3.getY(), (float) vec3.getZ()));
    }

    @Override
    public VertexFormat getFormat() {
        return vertexFormat;
    }

    @Override
    public Pair<IPerspectiveAwareModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        if (transformMap == null) {
            transformMap = new EnumMap<ItemCameraTransforms.TransformType, Matrix4f>(ItemCameraTransforms.TransformType.class);
        }
        if (!transformMap.containsKey(cameraTransformType)) {
            RenderTransformation trans = model.getGenModel().getTransformation(getRenderPlace(cameraTransformType));
            Matrix4f mat = new Matrix4f();
            mat.setIdentity();
            Log.debug(cameraTransformType);
            if (trans != null) {
                Vect3d t = trans.getTranslation();
                Vect3d r = trans.getRotation();
                Vect3d s = trans.getScale();
                org.lwjgl.util.vector.Matrix4f mat2 = new org.lwjgl.util.vector.Matrix4f();
                mat2.translate(new Vector3f((float) t.getX(), (float) t.getY(), (float) t.getZ()));

                mat2.rotate((float) Math.toRadians(r.getX()), new Vector3f(1, 0, 0));
                mat2.rotate((float) Math.toRadians(r.getY()), new Vector3f(0, 1, 0));
                mat2.rotate((float) Math.toRadians(r.getZ()), new Vector3f(0, 0, 1));

                mat2.scale(new Vector3f((float) s.getX(), (float) s.getY(), (float) s.getZ()));
                FloatBuffer buff = BufferUtils.createFloatBuffer(16);
                mat2.store(buff);
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        mat.setElement(i, j, buff.get(i * 4 + j));
                    }
                }
            }
            transformMap.put(cameraTransformType, mat);
        }
        return new ImmutablePair<IPerspectiveAwareModel, Matrix4f>(this, transformMap.get(cameraTransformType));
    }

    private RenderPlace getRenderPlace(ItemCameraTransforms.TransformType cameraTransformType) {
        switch (cameraTransformType) {
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
            default:
                return RenderPlace.NONE;
        }
    }
}
