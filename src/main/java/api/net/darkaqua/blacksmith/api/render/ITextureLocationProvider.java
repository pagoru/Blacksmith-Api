package net.darkaqua.blacksmith.api.render;

import net.darkaqua.blacksmith.api.util.Tuple2;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface ITextureLocationProvider {

    TextureLocation getTextureLocation(IRenderShape shape);

    /**
     *
     * @return an array this all textures that this provider can supply
     */
    Tuple2<String, TextureLocation>[] getAllTextures();
}
