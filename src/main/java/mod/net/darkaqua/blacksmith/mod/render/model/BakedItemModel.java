package net.darkaqua.blacksmith.mod.render.model;

import net.darkaqua.blacksmith.api.render.model.providers.IItemModelProvider;
import net.darkaqua.blacksmith.api.render.model.IStaticModel;
import net.darkaqua.blacksmith.mod.registry.RenderRegistry;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ISmartItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cout970 on 27/12/2015.
 */
public class BakedItemModel implements ISmartItemModel {

    @Override
    public IBakedModel handleItemState(ItemStack stack) {
        IItemModelProvider provider = RenderRegistry.INSTANCE.getItemModelProvider(stack.getItem());
        IStaticModel id = provider.getModelForVariant(MCInterface.fromItemStack(stack));
        return RenderRegistry.INSTANCE.getBakedModel(id);
    }

    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing p_177551_1_) {
        return new ArrayList<>(0);
    }

    @Override
    public List<BakedQuad> getGeneralQuads() {
        return new ArrayList<>(0);
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
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
