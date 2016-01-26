package net.darkaqua.blacksmith.mod.render.model;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.ResourceLocation;
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

    public IBakedModel build() {
        ItemLayerModel model = new ItemLayerModel(ImmutableList.copyOf(textures));
        return model.bake(TRSRTransformation.identity(), DefaultVertexFormats.ITEM, new Function<ResourceLocation, TextureAtlasSprite>() {
            @Nullable
            @Override
            public TextureAtlasSprite apply(@Nullable ResourceLocation input) {
                return sprites.get(input);
            }
        });
    }

    @Override
    public void onTexturesLoad(TextureMap textureGetter) {
        for(ResourceLocation r : textures){
            sprites.put(r, textureGetter.registerSprite(r));
        }
    }
}
