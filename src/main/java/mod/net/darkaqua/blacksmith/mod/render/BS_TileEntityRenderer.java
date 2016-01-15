package net.darkaqua.blacksmith.mod.render;

import net.darkaqua.blacksmith.api.render.gui.IFontRenderer;
import net.darkaqua.blacksmith.api.render.tileentity.ITileEntityRenderer;
import net.darkaqua.blacksmith.api.render.tileentity.ITileEntityRendererHelper;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.darkaqua.blacksmith.mod.registry.RenderRegistry;
import net.darkaqua.blacksmith.mod.tileentity.BS_TileEntity;
import net.darkaqua.blacksmith.mod.tileentity.TileEntityWrapper;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by cout970 on 16/12/2015.
 */
public class BS_TileEntityRenderer extends TileEntitySpecialRenderer<BS_TileEntity> {

    public static final BS_TileEntityRenderer INSTANCE = new BS_TileEntityRenderer();
    public static final TileEntityRendererHelper HELPER = new TileEntityRendererHelper(DESTROY_STAGES);

    @Override
    @SuppressWarnings(value = "unchecked")
    public void renderTileEntityAt(BS_TileEntity tile, double posX, double posY, double posZ, float partialTick, int breakingProgress) {
        ITileEntityRenderer renderer = RenderRegistry.INSTANCE.getTileEntityRenderer(tile.getTileEntityDefinition().getClass());
        if (renderer != null) {
            getWorld().theProfiler.startSection(tile.getTileEntityDefinition().getClass() + "");
            renderer.renderTileEntity(new TileEntityWrapper(tile), tile.getTileEntityDefinition(), HELPER, new Vect3d(posX, posY, posZ), partialTick, breakingProgress);
            getWorld().theProfiler.endSection();
        }
    }

    public static class TileEntityRendererHelper implements ITileEntityRendererHelper {

        private ResourceReference[] breakingTextures;

        private TileEntityRendererHelper(ResourceLocation[] breaking) {
            breakingTextures = new ResourceReference[breaking.length];
            for (int i = 0; i < breaking.length; i++) {
                breakingTextures[i] = new ResourceReference(breaking[i].getResourceDomain(), breaking[i].getResourcePath());
            }
        }

        @Override
        public ResourceReference[] getBreakingTexture() {
            return breakingTextures;
        }

        @Override
        public void bindTexture(ResourceReference texture) {
            INSTANCE.bindTexture(new ResourceLocation(texture.getDomain(), texture.getPath()));
        }

        @Override
        public IFontRenderer getFontRenderer() {
            return MCInterface.fromFontRenderer(INSTANCE.getFontRenderer());
        }

        @Override
        public IWorld getWorld() {
            return MCInterface.fromWorld(INSTANCE.getWorld());
        }
    }
}
