package net.darkaqua.blacksmith.api.client.render.tileentity;

import net.darkaqua.blacksmith.api.common.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;

/**
 * Created by cout970 on 16/12/2015.
 */
@Implementable
public interface ITileEntityRenderer<T extends ITileEntityDefinition> {

    void renderTileEntity(ITileEntity tile, T def, ITileEntityRendererHelper helper, Vect3d offset, float partialTick, int breakingProgress);
}
