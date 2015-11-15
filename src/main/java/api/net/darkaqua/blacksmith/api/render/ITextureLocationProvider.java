package net.darkaqua.blacksmith.api.render;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface ITextureLocationProvider {

    TextureLocation getTextureLocation(int type, int metadata);
}
