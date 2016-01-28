package net.darkaqua.blacksmith.mod.render.model;

import net.darkaqua.blacksmith.api.render.model.IModelPartIdentifier;
import net.darkaqua.blacksmith.api.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.render.model.RenderTransformation;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.mod.registry.ModelRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.client.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.util.vector.Vector3f;

import javax.vecmath.Matrix4f;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by cout970 on 27/12/2015.
 */
public class RenderModelWrapper implements IPerspectiveAwareModel {

    private IStaticModel model;
    protected List<BakedQuad> generalQuads;
    protected EnumMap<EnumFacing, List<BakedQuad>> faceQuads;
    protected TextureAtlasSprite texture;
    protected ItemCameraTransforms trans;
    protected EnumMap<ItemCameraTransforms.TransformType, Matrix4f> transformMap;

    public RenderModelWrapper(IStaticModel model) {
        this.model = model;
        faceQuads = new EnumMap<>(EnumFacing.class);
        for (EnumFacing e : EnumFacing.values()) {
            faceQuads.put(e, new ArrayList<>());
        }
        generalQuads = new ArrayList<>();
        transformMap = new EnumMap<>(ItemCameraTransforms.TransformType.class);
        for (IModelPartIdentifier id : model.getParts()) {
            IBakedModelPart baked = ModelRegistry.INSTANCE.getBakedModelPart(id);
            if (baked == null) {
                throw new IllegalStateException("IStaticModel: " + model + ", has a part that was not registered");
            }
            generalQuads.addAll(baked.getGeneralQuads());
            for (EnumFacing e : EnumFacing.values()) {
                List<BakedQuad> list = baked.getFaceQuads(e);
                faceQuads.get(e).addAll(list);
            }
            if (texture == null) {
                texture = baked.getParticleTexture();
            }
        }
        if (texture == null) {
            texture = Minecraft.getMinecraft().getTextureMapBlocks().registerSprite(new ResourceLocation("missingno"));
        }
    }

    public IStaticModel getModel() {
        return model;
    }

    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing side) {
        return faceQuads.get(side);
    }

    @Override
    public List<BakedQuad> getGeneralQuads() {
        return generalQuads;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return model.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return model.needsInventoryRotation();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return texture;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        if (trans == null) {
            ItemTransformVec3f thirdPerson = toTransformVec(model.getTransformation(RenderPlace.THIRD_PERSON));
            ItemTransformVec3f firstPerson = toTransformVec(model.getTransformation(RenderPlace.FIRST_PERSON));
            ItemTransformVec3f head = toTransformVec(model.getTransformation(RenderPlace.HEAD));
            ItemTransformVec3f gui = toTransformVec(model.getTransformation(RenderPlace.GUI));
            ItemTransformVec3f ground = toTransformVec(model.getTransformation(RenderPlace.GROUND));
            ItemTransformVec3f fixed = toTransformVec(model.getTransformation(RenderPlace.FIXED));
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
    public Pair<IPerspectiveAwareModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        if (transformMap == null) {
            transformMap = new EnumMap<>(ItemCameraTransforms.TransformType.class);
        }
//        if (!transformMap.containsKey(cameraTransformType)) {
        RenderTransformation trans = model.getTransformation(getRenderPlace(cameraTransformType));
        TRSRTransformation transf = new TRSRTransformation(toTransformVec(trans));
        transformMap.put(cameraTransformType, TRSRTransformation.blockCornerToCenter(transf).getMatrix());
//        }
        return new ImmutablePair<>(this, transformMap.get(cameraTransformType));
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

    @Override
    public VertexFormat getFormat() {
        return DefaultVertexFormats.ITEM;
    }
}
