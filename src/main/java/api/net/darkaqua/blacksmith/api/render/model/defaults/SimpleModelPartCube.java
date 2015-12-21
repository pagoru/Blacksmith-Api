package net.darkaqua.blacksmith.api.render.model.defaults;

import net.darkaqua.blacksmith.api.render.model.IModelPart;
import net.darkaqua.blacksmith.api.render.model.IModelQuad;
import net.darkaqua.blacksmith.api.util.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cout970 on 07/12/2015.
 */
public class SimpleModelPartCube implements IModelPart {

    protected ResourceReference texture;
    protected List<IModelQuad> quads;
    protected Vect3d size;
    protected Vect3d pos;
    protected Vect3d offset;
    protected Vect3d rotation;
    protected int textureSize = 32;
    protected Vect2i textureOffset;

    public SimpleModelPartCube(ResourceReference all) {
        texture = all;
    }

    @Override
    public List<IModelQuad> getQuads() {
        if (quads == null) {
            generateQuads();
        }
        return quads;
    }

    private void generateQuads() {
        quads = new ArrayList<>(6);
        if (size == null) {
            size = new Vect3d(1, 1, 1);
        }
        if (textureOffset == null){
            textureOffset = new Vect2i(0,0);
        }
        for (Direction dir : Direction.values()) {
            quads.add(new Quad(dir, texture, size, textureSize, textureOffset));
        }
        for (IModelQuad p : quads) {
            Quad q = (Quad) p;
            for (int i = 0; i < 4; i++) {
                if (offset != null) {
                    q.vertex[i].add(offset);
                }
                if (rotation != null) {
                    q.vertex[i].rotateX(rotation.getX());
                    q.vertex[i].rotateY(rotation.getY());
                    q.vertex[i].rotateZ(rotation.getZ());
                }
                if (pos != null) {
                    q.vertex[i].add(pos);
                }
            }
        }
    }

    public void setSize(Vect3d cubeSize) {
        size = cubeSize.copy();
    }

    public void setPosition(Vect3d cubePosition) {
        pos = cubePosition.copy();
    }

    public void setOffset(Vect3d cubeOffset) {
        offset = cubeOffset.copy();
    }

    public void setRotation(Vect3d cubeRotation) {
        rotation = cubeRotation.copy();
    }

    public void setTextureOffset(Vect2i cubeTextureOffset) {
        textureOffset = cubeTextureOffset.copy();
    }

    public void setTextureSize(int size) {
        textureSize = size;
    }

    private static class Quad implements IModelQuad {

        private static final double[][][] FULL_UV = new double[][][]{
                {{0, 1}, {1, 1}, {1, 0}, {0, 0}},//DOWN
                {{0, 0}, {0, 1}, {1, 1}, {1, 0}},//UP
                {{1, 1}, {1, 0}, {0, 0}, {0, 1}},//NORTH
                {{0, 1}, {1, 1}, {1, 0}, {0, 0}},//SOUTH
                {{0, 1}, {1, 1}, {1, 0}, {0, 0}},//WEST
                {{1, 1}, {1, 0}, {0, 0}, {0, 1}},//EAST
        };

        private static final double[][][] VERTEX = new double[][][]{
                {{0, 0, 0}, {1, 0, 0}, {1, 0, 1}, {0, 0, 1}},//DOWN
                {{0, 1, 0}, {0, 1, 1}, {1, 1, 1}, {1, 1, 0}},//UP
                {{0, 0, 0}, {0, 1, 0}, {1, 1, 0}, {1, 0, 0}},//NORTH
                {{0, 0, 1}, {1, 0, 1}, {1, 1, 1}, {0, 1, 1}},//SOUTH
                {{0, 0, 0}, {0, 0, 1}, {0, 1, 1}, {0, 1, 0}},//WEST
                {{1, 0, 0}, {1, 1, 0}, {1, 1, 1}, {1, 0, 1}} //EAST
        };

        private Direction side;
        private ResourceReference texture;
        private Vect3d[] vertex;
        private Vect2d[] uv;

        public Quad(Direction dir, ResourceReference texture, Vect3d size, int textureSize, Vect2i textureOffset) {
            this.side = dir;
            this.texture = texture;
            vertex = new Vect3d[4];
            uv = new Vect2d[4];
            for (int i = 0; i < 4; i++) {
                vertex[i] = new Vect3d(VERTEX[side.ordinal()][i]).multiply(size);
            }
            double pixel = 1d / textureSize;
            size = size.copy().multiply(16);
            switch (dir) {

                case DOWN:
                    uv[0] = new Vect2d((textureOffset.getX() + size.getZ() + size.getX()) * pixel,      (textureOffset.getY() + size.getZ()) * pixel);
                    uv[1] = new Vect2d((textureOffset.getX() + size.getZ() + size.getX()) * pixel,      textureOffset.getY() * pixel);
                    uv[2] = new Vect2d((textureOffset.getX() + size.getZ() + size.getX() * 2) * pixel,  textureOffset.getY() * pixel);
                    uv[3] = new Vect2d((textureOffset.getX() + size.getZ() + size.getX() * 2) * pixel,  (textureOffset.getY() + size.getZ()) * pixel);
                    break;
                case UP:
                    uv[0] = new Vect2d((textureOffset.getX() + size.getZ()) * pixel,                (textureOffset.getY() + size.getZ()) * pixel);
                    uv[1] = new Vect2d((textureOffset.getX() + size.getZ()) * pixel,                textureOffset.getY() * pixel);
                    uv[2] = new Vect2d((textureOffset.getX() + size.getZ() + size.getX()) * pixel,  textureOffset.getY() * pixel);
                    uv[3] = new Vect2d((textureOffset.getX() + size.getZ() + size.getX()) * pixel,  (textureOffset.getY() + size.getZ()) * pixel);
                    break;
                case NORTH://Front
                    uv[0] = new Vect2d((textureOffset.getX() + size.getZ()) * pixel,                (textureOffset.getY() + size.getZ() + size.getY()) * pixel);
                    uv[1] = new Vect2d((textureOffset.getX() + size.getZ()) * pixel,                (textureOffset.getY() + size.getZ()) * pixel);
                    uv[2] = new Vect2d((textureOffset.getX() + size.getZ() + size.getX()) * pixel,  (textureOffset.getY() + size.getZ()) * pixel);
                    uv[3] = new Vect2d((textureOffset.getX() + size.getZ() + size.getX()) * pixel,  (textureOffset.getY() + size.getZ() + size.getY()) * pixel);
                    break;
                case SOUTH://Back
                    uv[0] = new Vect2d((textureOffset.getX() + size.getZ()*2 + size.getX()*2) * pixel, (textureOffset.getY() + size.getZ() + size.getY()) * pixel);
                    uv[1] = new Vect2d((textureOffset.getX() + size.getZ()*2 + size.getX()) * pixel,   (textureOffset.getY() + size.getZ() + size.getY()) * pixel);
                    uv[2] = new Vect2d((textureOffset.getX() + size.getZ()*2 + size.getX()) * pixel,   (textureOffset.getY() + size.getZ()) * pixel);
                    uv[3] = new Vect2d((textureOffset.getX() + size.getZ()*2 + size.getX()*2) * pixel, (textureOffset.getY() + size.getZ()) * pixel);
                    break;
                case WEST://Left
                    uv[0] = new Vect2d((textureOffset.getX() + size.getZ()) * pixel,    (textureOffset.getY() + size.getZ() + size.getY()) * pixel);
                    uv[1] = new Vect2d(textureOffset.getX() * pixel,                    (textureOffset.getY() + size.getZ() + size.getY()) * pixel);
                    uv[2] = new Vect2d(textureOffset.getX() * pixel,                    (textureOffset.getY() + size.getZ()) * pixel);
                    uv[3] = new Vect2d((textureOffset.getX() + size.getZ()) * pixel,    (textureOffset.getY() + size.getZ()) * pixel);
                    break;
                case EAST://Right
                    uv[0] = new Vect2d((textureOffset.getX() + size.getZ() + size.getX()) * pixel,   (textureOffset.getY() + size.getZ() + size.getY()) * pixel);
                    uv[1] = new Vect2d((textureOffset.getX() + size.getZ() + size.getX()) * pixel,   (textureOffset.getY() + size.getZ()) * pixel);
                    uv[2] = new Vect2d((textureOffset.getX() + size.getZ()*2 + size.getX()) * pixel, (textureOffset.getY() + size.getZ()) * pixel);
                    uv[3] = new Vect2d((textureOffset.getX() + size.getZ()*2 + size.getX()) * pixel, (textureOffset.getY() + size.getZ() + size.getY()) * pixel);
                    break;
            }
        }

        @Override
        public Vect3d getVertex(QuadVertex pos) {
            return vertex[pos.ordinal()];
        }

        @Override
        public Vect2d getUV(QuadVertex pos) {
            return uv[pos.ordinal()];
        }

        @Override
        public Direction getNormal() {
            return null;
        }

        @Override
        public ResourceReference getTexture() {
            return texture;
        }

        @Override
        public boolean useShade() {
            return true;
        }
    }
}
