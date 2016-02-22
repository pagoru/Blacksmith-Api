package net.darkaqua.blacksmith.mod.client.render.model;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.client.model.TRSRTransformation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cout970 on 27/12/2015.
 */
public class ItemLayerModelBuilder implements IModelBuilder {

    private List<ResourceLocation> textures;
    private Map<ResourceLocation, TextureAtlasSprite> sprites;

    public ItemLayerModelBuilder(List<ResourceLocation> texture) {
        this.textures = texture;
        sprites = new HashMap<>();
    }

    public IBakedModelPart build() {
        ItemLayerModel model = new ItemLayerModel(ImmutableList.copyOf(textures));
        return new BakedModelPartWrapper(model.bake(TRSRTransformation.identity(), DefaultVertexFormats.ITEM, new Function<ResourceLocation, TextureAtlasSprite>() {
            @Nullable
            @Override
            public TextureAtlasSprite apply(@Nullable ResourceLocation input) {
                return sprites.get(input);
            }
        }));
    }

    @Override
    public void onTexturesLoad(TextureMap textureGetter) {
        for (ResourceLocation r : textures) {
            sprites.put(r, textureGetter.registerSprite(r));
        }
    }

    private class BakedModelPartWrapper implements IBakedModelPart {

        private IFlexibleBakedModel model;

        public BakedModelPartWrapper(IFlexibleBakedModel bake) {
            model = bake;
        }

        @Override
        public List<BakedQuad> getFaceQuads(EnumFacing side) {
            return model.getFaceQuads(side);
        }

        @Override
        public List<BakedQuad> getGeneralQuads() {
            return model.getGeneralQuads();
        }

        @Override
        public TextureAtlasSprite getParticleTexture() {
            return model.getParticleTexture();
        }
    }
}
