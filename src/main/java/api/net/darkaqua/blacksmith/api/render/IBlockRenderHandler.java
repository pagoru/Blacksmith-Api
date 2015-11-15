package net.darkaqua.blacksmith.api.render;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface IBlockRenderHandler {

    /**
     * Returns the ITextureLocationProvider that provides textures for this block, the args:
     * type will be 0, and metadata will the block metadata
     * @return the TextureLocationProvider that provide the block textures
     */
    ITextureLocationProvider getTextureProvider();

    IBlockModel getBlockModel();
}
