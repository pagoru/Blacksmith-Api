package net.darkaqua.blacksmith.mod.render.util;

import net.darkaqua.blacksmith.api.util.Vect2d;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;

/**
 * Created by cout970 on 07/12/2015.
 */
public class UnBakedQuad {

    private static final float[] FACE_BRIGHTNESS = {0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F};
    private EnumFacing normal;
    private Vect3d[] vertex;
    private Vect2d[] uvs;
    private boolean shade;
    private int aux;

    public UnBakedQuad(EnumFacing normal){
        vertex = new Vect3d[4];
        uvs = new Vect2d[4];
        this.normal = normal;
    }

    public void addVertex(Vect3d pos, Vect2d uv){
        if(aux >= 4){
            throw new IllegalStateException("Unable to add more than 4 vertex to a UnBakedQuad");
        }
        vertex[aux] = new Vect3d(pos);
        uvs[aux] = new Vect2d(uv);
        aux++;
    }

    public BakedQuad bake(){
        int[] vertexData = new int[7*4];
        int color = !shade ? -1 : getFaceShadeColor();
        for(int i = 0; i < 4 ; i++)
            fillVertexData(vertexData, i, vertex[i], color, uvs[i]);
        return new BakedQuad(vertexData, -1, normal);
    }

    public static void fillVertexData(int[] faceData, int storeIndex, Vect3d position, int shadeColor, Vect2d uv) {
        int l = storeIndex * 7;
        faceData[l] = Float.floatToRawIntBits((float)position.getX());
        faceData[l + 1] = Float.floatToRawIntBits((float)position.getY());
        faceData[l + 2] = Float.floatToRawIntBits((float)position.getZ());
        faceData[l + 3] = shadeColor;
        faceData[l + 4] = Float.floatToRawIntBits((float) uv.getX());
        faceData[l + 5] = Float.floatToRawIntBits((float) uv.getY());
    }

    private int getFaceShadeColor(){
        float f = normal == null ? 1F : FACE_BRIGHTNESS[normal.ordinal()];
        int i = MathHelper.clamp_int((int)(f * 255.0F), 0, 255);
        return 0xFF000000 | i << 16 | i << 8 | i;
    }

    public void setShade(boolean shade) {
        this.shade = shade;
    }
}
