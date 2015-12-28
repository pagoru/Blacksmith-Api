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

/**
 * Created by cout970 on 27/12/2015.
 */
public class ItemLayerModelBuilder implements IModelBuilder {

    private ResourceLocation texture;
    private TextureAtlasSprite sprite;

    public ItemLayerModelBuilder(ResourceLocation texture){
        this.texture = texture;
    }

    public IBakedModel build(){
        ItemLayerModel model = new ItemLayerModel(ImmutableList.of(texture));
        IBakedModel b = model.bake(TRSRTransformation.identity(), DefaultVertexFormats.ITEM, new Function<ResourceLocation, TextureAtlasSprite>() {
            @Nullable
            @Override
            public TextureAtlasSprite apply(@Nullable ResourceLocation input) {
                return sprite;
            }
        });
        return b;
    }

    @Override
    public void onTexturesLoad(TextureMap textureGetter) {
        sprite = textureGetter.registerSprite(texture);
    }
}
