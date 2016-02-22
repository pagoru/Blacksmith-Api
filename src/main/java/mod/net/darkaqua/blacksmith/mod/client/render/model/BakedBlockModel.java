package net.darkaqua.blacksmith.mod.client.render.model;

import net.darkaqua.blacksmith.api.client.render.block.IBlockModelProvider;
import net.darkaqua.blacksmith.api.client.render.model.IStaticModel;
import net.darkaqua.blacksmith.mod.common.registry.RenderRegistry;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ISmartBlockModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cout970 on 27/12/2015.
 */
public class BakedBlockModel implements ISmartBlockModel {

    protected Block block;

    public BakedBlockModel(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public IBakedModel handleBlockState(IBlockState state) {
        IBlockModelProvider provider = RenderRegistry.INSTANCE.getBlockModelProvider(state.getBlock());
        IStaticModel id = provider.getModelForBlockData(MCInterface.fromBlockState(state));
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
        IBlockModelProvider provider = RenderRegistry.INSTANCE.getBlockModelProvider(block);
        IStaticModel id = provider.getModelForBlockData(MCInterface.fromBlockState(block.getDefaultState()));
        IBakedModel model = RenderRegistry.INSTANCE.getBakedModel(id);
        return model.getParticleTexture();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return null;
    }
}
