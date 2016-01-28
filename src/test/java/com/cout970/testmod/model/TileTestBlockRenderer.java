package com.cout970.testmod.model;

import com.cout970.testmod.tile.TileTestBlock;
import net.darkaqua.blacksmith.api.render.model.IDynamicModel;
import net.darkaqua.blacksmith.api.render.tileentity.ITileEntityRenderer;
import net.darkaqua.blacksmith.api.render.tileentity.ITileEntityRendererHelper;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.Vect3d;
import org.lwjgl.opengl.GL11;

/**
 * Created by cout970 on 28/01/2016.
 */
public class TileTestBlockRenderer implements ITileEntityRenderer<TileTestBlock> {

    public static IDynamicModel model;

    @Override
    public void renderTileEntity(ITileEntity tile, TileTestBlock def, ITileEntityRendererHelper helper, Vect3d offset, float partialTick, int breakingProgress) {
        if (def.list == -1){
            def.list = GL11.glGenLists(1);
            GL11.glNewList(def.list, GL11.GL_COMPILE_AND_EXECUTE);
            model.setRenderData(def.getParent().getWorldRef(), Vect3d.nullVector());
            model.renderAll();
            GL11.glEndList();
        }else{
            GL11.glPushMatrix();
            GL11.glTranslated(offset.getX(), offset.getY(), offset.getZ());
            GL11.glCallList(def.list);
            GL11.glPopMatrix();
        }
    }
}
