package net.darkaqua.blacksmith.api.render.tileentity;

import net.darkaqua.blacksmith.api.gui.IFontRenderer;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.world.IWorld;

/**
 * Created by cout970 on 16/12/2015.
 */
public interface ITileEntityRendererHelper {

    ResourceReference[] getBreakingTexture();

    void bindTexture(ResourceReference texture);

    IFontRenderer getFontRenderer();

    IWorld getWorld();
}
