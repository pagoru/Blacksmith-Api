package net.darkaqua.blacksmith.api.render.model.defaults;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.render.model.IModelPart;
import net.darkaqua.blacksmith.api.render.model.IModelQuad;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect2d;
import net.darkaqua.blacksmith.api.util.Vect3d;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 24/01/2016.
 */
public class ModelPartBuilder {

    protected ResourceReference texture;
    protected List<IModelQuad> quads;
    protected Vect2d tempUV;
    protected TempData[] tempVertex = new TempData[4];
    protected Direction side;
    protected int vertex;
    protected boolean useShade;

    public ModelPartBuilder(ResourceReference texture) {
        this.texture = texture;
        quads = new LinkedList<>();
        tempUV = Vect2d.nullVector();
    }

    public void addVertex(Vect3d pos) {
        tempVertex[vertex] = new TempData(pos.copy(), tempUV);
        vertex++;
        if (vertex >= 4) {
            vertex = 0;
            Vect3d[] vertexArray = new Vect3d[4];
            Vect2d[] uv = new Vect2d[4];
            for (int i = 0; i < 4; i++) {
                vertexArray[i] = tempVertex[i].getVextex();
                uv[i] = tempVertex[i].getUV();
            }
            ModelQuad mQuad = new ModelQuad(vertexArray, uv, side, texture, useShade);
            quads.add(mQuad);
        }
    }

    public void addVertex(double x, double y, double z) {

    }

    public void addUV(Vect2d uv) {
        tempUV = uv.copy();
    }

    public void addUV(double u, double v) {
        tempUV = new Vect2d(u, v);
    }

    public void addVertexWithUV(Vect3d pos, Vect2d uv) {
        addVertexWithUV(pos.getX(), pos.getY(), pos.getZ(), uv.getX(), uv.getY());
    }

    public void addVertexWithUV(double x, double y, double z, double u, double v) {
        addUV(u, v);
        addVertex(x, y, z);
    }

    public IModelPart build() {
        if (vertex != 0) {
            throw new IllegalStateException("ModelPartBuilder cannot build an IModelPart because there are missing vertex, stored vertex: " + vertex + " of 4");
        }
        ModelPart part = new ModelPart(Lists.newArrayList(quads));
        quads.clear();
        return part;
    }

    public boolean isUseShade() {
        return useShade;
    }

    public void setUseShade(boolean useShade) {
        this.useShade = useShade;
    }

    public ResourceReference getTexture() {
        return texture;
    }

    public void setTexture(ResourceReference texture) {
        this.texture = texture;
    }

    public Direction getSide() {
        return side;
    }

    public void setSide(Direction side) {
        this.side = side;
    }

    private class TempData {
        private Vect3d vextex;
        private Vect2d uv;

        public TempData(Vect3d vextex, Vect2d uv) {
            this.vextex = vextex;
            this.uv = uv;
        }

        public Vect3d getVextex() {
            return vextex;
        }

        public Vect2d getUV() {
            return uv;
        }
    }

    public static class ModelPart implements IModelPart {

        protected List<IModelQuad> quads;

        public ModelPart(List<IModelQuad> quads) {
            this.quads = quads;
        }

        @Override
        public List<IModelQuad> getQuads() {
            return quads;
        }
    }

    public static class ModelQuad implements IModelQuad {

        protected Vect3d[] vextex;
        protected Vect2d[] uv;
        protected Direction side;
        protected ResourceReference texture;
        protected boolean useShade;

        public ModelQuad(Vect3d[] vextex, Vect2d[] uv, Direction side, ResourceReference texture, boolean useShade) {
            this.vextex = vextex;
            this.uv = uv;
            this.side = side;
            this.texture = texture;
            this.useShade = useShade;
        }

        @Override
        public Vect3d getVertex(QuadVertex pos) {
            return vextex[pos.ordinal()];
        }

        @Override
        public Vect2d getUV(QuadVertex pos) {
            return uv[pos.ordinal()];
        }

        @Override
        public Direction getSide() {
            return side;
        }

        @Override
        public ResourceReference getTexture() {
            return texture;
        }

        @Override
        public boolean useShade() {
            return useShade;
        }
    }
}
