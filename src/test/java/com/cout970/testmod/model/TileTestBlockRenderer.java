package com.cout970.testmod.model;

import com.cout970.testmod.ModClass;
import com.cout970.testmod.tile.TileTestBlock;
import net.darkaqua.blacksmith.api.client.render.model.IDynamicModel;
import net.darkaqua.blacksmith.api.client.render.tileentity.ITileEntityRenderer;
import net.darkaqua.blacksmith.api.client.render.tileentity.ITileEntityRendererHelper;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import org.lwjgl.opengl.GL11;

/**
 * Created by cout970 on 28/01/2016.
 */
public class TileTestBlockRenderer implements ITileEntityRenderer<TileTestBlock> {

    public static IDynamicModel model;
    public static TestModel techneModel = new TestModel();

    @Override
    public void renderTileEntity(ITileEntity tile, TileTestBlock def, ITileEntityRendererHelper helper, Vect3d offset, float partialTick, int breakingProgress) {
        render1(def, offset, helper);
    }

    private static void render1(TileTestBlock def, Vect3d offset, ITileEntityRendererHelper helper) {
        model.setOffset(offset);
        model.renderAll();
    }

    private static void render2(TileTestBlock def, Vect3d offset, ITileEntityRendererHelper helper) {
        GL11.glPushMatrix();
        GL11.glTranslated(offset.getX() + 0.5, offset.getY() + 1.5, offset.getZ() + 0.5);
        helper.bindTexture(new ResourceReference(ModClass.MOD_ID, "textures/models/test_block.png"));
        GL11.glRotatef(180, 1, 0, 0);
        GL11.glRotatef(180, 0, 1, 0);
        techneModel.render(0.0625f);
        GL11.glPopMatrix();
    }
}
