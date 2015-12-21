package net.darkaqua.blacksmith.api.render.tileentity;

import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.util.Vect3d;

/**
 * Created by cout970 on 16/12/2015.
 */
public interface ITileEntityRenderer<T extends ITileEntityDefinition> {

    void renderTileEntity(ITileEntity tile, T def, ITileEntityRendererHelper helper, Vect3d pos, float partialTick, int breakingProgress);
}
