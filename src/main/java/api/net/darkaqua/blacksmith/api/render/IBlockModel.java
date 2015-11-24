package net.darkaqua.blacksmith.api.render;

import java.util.List;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface IBlockModel {

    List<IRenderShape> getShapes();

    boolean useAmbientOcclusion();

    boolean render3DGui();

    ITextureLocationProvider getTextureProvider();
}
