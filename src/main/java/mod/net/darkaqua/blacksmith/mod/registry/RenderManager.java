package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IRenderManager;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;

/**
 * Created by cout970 on 20/12/2015.
 */
public class RenderManager implements IRenderManager {

    public static final RenderManager INSTANCE = new RenderManager();

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
                float f3 = ibakedmodel.getItemCameraTransforms().field_181699_o.scale.x;
                float f4 = ibakedmodel.getItemCameraTransforms().field_181699_o.scale.y;
                float f5 = ibakedmodel.getItemCameraTransforms().field_181699_o.scale.z;
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
}
