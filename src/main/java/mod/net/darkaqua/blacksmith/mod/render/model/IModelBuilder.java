package net.darkaqua.blacksmith.mod.render.model;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;

/**
 * Created by cout970 on 27/12/2015.
 */
public interface IModelBuilder {

    void onTexturesLoad(TextureMap textureGetter);

    IBakedModel build();
}
