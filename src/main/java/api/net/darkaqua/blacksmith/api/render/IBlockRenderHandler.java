package net.darkaqua.blacksmith.api.render;

import net.darkaqua.blacksmith.api.block.blockstate.IBlockVariant;
import net.darkaqua.blacksmith.api.render.model.json.IJsonModelWrapper;

import java.util.List;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface IBlockRenderHandler {

    List<IJsonModelWrapper> getBlockModelsForState(IBlockVariant state);
}
