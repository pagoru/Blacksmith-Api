package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IRenderManager;
import net.darkaqua.blacksmith.api.render.model.IPartIdentifier;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.Vect3i;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.render.model.IBakedModelPart;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 20/12/2015.
 */
public class RenderManager implements IRenderManager {

    public static final RenderManager INSTANCE = new RenderManager();
    private CachedModel dummyModel = new CachedModel();

    public static void init() {
    }

    @Override
    public void renderItemStack(IItemStack stack, Vect3d pos, RenderPlace place) {
        ItemStack itemstack = MCInterface.toItemStack(stack);
        net.minecraft.client.renderer.entity.RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();

        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        renderManager.renderEngine.getTexture(TextureMap.locationBlocksTexture).setBlurMipmap(false, false);

        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.pushMatrix();
        IBakedModel ibakedmodel = itemRenderer.getItemModelMesher().getItemModel(itemstack);
        int i = stack.getAmount();
        for (int j = 0; j < i; ++j) {
            if (ibakedmodel.isGui3d()) {
                GlStateManager.pushMatrix();
                GlStateManager.scale(0.5F, 0.5F, 0.5F);
                ibakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.GROUND);
                itemRenderer.renderItem(itemstack, ibakedmodel);
                GlStateManager.popMatrix();
            } else {
                GlStateManager.pushMatrix();
                ibakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.GROUND);
                itemRenderer.renderItem(itemstack, ibakedmodel);
                GlStateManager.popMatrix();
                float f3 = ibakedmodel.getItemCameraTransforms().ground.scale.x;
                float f4 = ibakedmodel.getItemCameraTransforms().ground.scale.y;
                float f5 = ibakedmodel.getItemCameraTransforms().ground.scale.z;
                GlStateManager.translate(0.0F * f3, 0.0F * f4, 0.046875F * f5);
            }
        }
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        renderManager.renderEngine.getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
    }

    @Override
    public void bindTexture(ResourceReference resourceReference) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(MCInterface.toResourceLocation(resourceReference));
    }

    @Override
    public void renderModelPartsStaticLight(List<IPartIdentifier> parts, WorldRef ref, Vect3d offset) {
        if (parts.isEmpty()) return;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();

        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableBlend();
        GlStateManager.disableCull();

        if (Minecraft.isAmbientOcclusionEnabled()) {
            GlStateManager.shadeModel(GL11.GL_SMOOTH);
        } else {
            GlStateManager.shadeModel(GL11.GL_FLAT);
        }
        IBlockAccess world = MinecraftForgeClient.getRegionRenderCache(MCInterface.toWorld(ref.getWorld()),
                MCInterface.toBlockPos(ref.getPosition()));

        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

        dummyModel.setBaked(parts.stream().map(ModelRegistry.INSTANCE::getBakedModelPart).collect(Collectors.toList()));
        Vect3i pos = ref.getPosition();
        worldRenderer.setTranslation(offset.getX() - pos.getX(), offset.getY() - pos.getY(), offset.getZ() - pos.getZ());
        Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer()
                .renderModel(world, dummyModel, MCInterface.toBlockState(ref.getBlockData()), MCInterface.toBlockPos(ref.getPosition()), worldRenderer, false);

        worldRenderer.setTranslation(0, 0, 0);
        tessellator.draw();
        RenderHelper.enableStandardItemLighting();
    }

    @Override
    public void bindBlocksTexture() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
    }

    @Override
    public void bindItemsTexture() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
    }

    @Override
    public void renderModelPartsDynamicLight(List<IPartIdentifier> parts) {
        if (parts.isEmpty()) return;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);

        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.OLDMODEL_POSITION_TEX_NORMAL);

        for (IPartIdentifier id : parts) {
            IBakedModelPart model = ModelRegistry.INSTANCE.getBakedModelPart(id);
            if (model == null) continue;
            for (BakedQuad quad : model.getGeneralQuads()) {
                int[] data = quad.getVertexData();
                for (int i = 0; i < 4; i++) {
                    byte bx = (byte) (data[6 + i * 7] & 0xFF);
                    byte by = (byte) ((data[6 + i * 7] >>> 0x08) & 0xFF);
                    byte bz = (byte) ((data[6 + i * 7] >>> 0x10) & 0xFF);
                    float nx = bx / 127f;
                    float ny = by / 127f;
                    float nz = bz / 127f;
                    Vector3f norm = new Vector3f(nx, ny, nz);
                    norm.normalise();

                    worldRenderer.pos(Float.intBitsToFloat(data[i * 7]), Float.intBitsToFloat(data[1 + i * 7]), Float.intBitsToFloat(data[2 + i * 7]))
                            .tex(Float.intBitsToFloat(data[4 + i * 7]), Float.intBitsToFloat(data[5 + i * 7])).normal(norm.getX(), norm.getY(), norm.getZ()).endVertex();
                }
            }
        }
        tessellator.draw();
    }

    private class CachedModel implements IBakedModel {

        public List<IBakedModelPart> baked;

        public List<IBakedModelPart> getBaked() {
            return baked;
        }

        public void setBaked(List<IBakedModelPart> baked) {

            this.baked = baked;
        }

        @Override
        public List<BakedQuad> getFaceQuads(EnumFacing side) {
            List<BakedQuad> list = new LinkedList<>();
            baked.stream().forEach(c -> list.addAll(c.getFaceQuads(side)));
            return list;
        }

        @Override
        public List<BakedQuad> getGeneralQuads() {
            List<BakedQuad> list = new LinkedList<>();
            baked.stream().forEach(c -> list.addAll(c.getGeneralQuads()));
            return list;
        }

        @Override
        public boolean isAmbientOcclusion() {
            return false;
        }

        @Override
        public boolean isGui3d() {
            return false;
        }

        @Override
        public boolean isBuiltInRenderer() {
            return false;
        }

        @Override
        public TextureAtlasSprite getParticleTexture() {
            return null;
        }

        @Override
        public ItemCameraTransforms getItemCameraTransforms() {
            return null;
        }
    }
}
