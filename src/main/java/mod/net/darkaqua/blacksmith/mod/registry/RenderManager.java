package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.IRenderManager;
import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

/**
 * Created by cout970 on 20/12/2015.
 */
public class RenderManager implements IRenderManager {

    public static final RenderManager INSTANCE = new RenderManager();

    public static void init(){}

    @Override
    public void renderItemStack(IItemStack stack, Vect3d pos, RenderPlace place) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        GL11.glPushMatrix();
        GL11.glTranslated(pos.getX(), pos.getY(), pos.getZ());
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        ItemStack itemstack = MCInterface.toItemStack(stack);
        IBakedModel model = renderItem.getItemModelMesher().getItemModel(itemstack);
        model = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(model, MCInterface.toCamaraTransform(place));
        renderItem.renderItem(itemstack, model);
        GL11.glPopMatrix();
    }

    @Override
    public void renderModel(IModelIdentifier identifier) {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        IBakedModel model = renderItem.getItemModelMesher().getModelManager().getModel(new ModelResourceLocation(identifier.getReference().toString(), ""));
        renderItem.renderItem(new ItemStack(new Item()), model);
    }

    @Override
    public void bindTexture(ResourceReference resourceReference) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(MCInterface.toResourceLocation(resourceReference));
    }
}
