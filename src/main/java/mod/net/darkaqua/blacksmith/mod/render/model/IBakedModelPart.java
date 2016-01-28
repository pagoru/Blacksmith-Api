package net.darkaqua.blacksmith.mod.render.model;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

import java.util.List;

/**
 * Created by cout970 on 27/01/2016.
 */
public interface IBakedModelPart {
    
    List<BakedQuad> getFaceQuads(EnumFacing side);

    List<BakedQuad> getGeneralQuads();

    TextureAtlasSprite getParticleTexture();
}
