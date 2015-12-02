package net.darkaqua.blacksmith.api.render;

import net.darkaqua.blacksmith.api.block.blockstate.IBlockVariant;
import net.darkaqua.blacksmith.api.render.model.IBlockModelWrapper;

import java.util.List;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface IBlockRenderHandler {

    //TODO test
    List<IBlockModelWrapper> getBlockModelsForState(IBlockVariant state);
}
