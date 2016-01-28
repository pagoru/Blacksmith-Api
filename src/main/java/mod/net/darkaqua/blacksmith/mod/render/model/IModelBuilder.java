package net.darkaqua.blacksmith.mod.render.model;

import net.minecraft.client.renderer.texture.TextureMap;

/**
 * Created by cout970 on 27/12/2015.
 */
public interface IModelBuilder {

    void onTexturesLoad(TextureMap textureGetter);

    IBakedModelPart build();
}
