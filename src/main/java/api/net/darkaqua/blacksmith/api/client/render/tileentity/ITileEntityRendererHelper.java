package net.darkaqua.blacksmith.api.client.render.tileentity;

import net.darkaqua.blacksmith.api.common.gui.IFontRenderer;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;
import net.darkaqua.blacksmith.api.common.world.IWorld;

/**
 * Created by cout970 on 16/12/2015.
 */
public interface ITileEntityRendererHelper {

    ResourceReference[] getBreakingTexture();

    void bindTexture(ResourceReference texture);

    IFontRenderer getFontRenderer();

    IWorld getWorld();

    void disableStandardItemLighting();

    void enableStandardItemLighting();
}
