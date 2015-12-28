package net.darkaqua.blacksmith.api.render.model.defaults;

import net.darkaqua.blacksmith.api.render.model.IModelPart;
import net.darkaqua.blacksmith.api.render.model.IModelQuad;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect2d;
import net.darkaqua.blacksmith.api.util.Vect3d;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by cout970 on 19/12/2015.
 */
public class SimpleModelPartBlock implements IModelPart {

    protected EnumMap<Direction, ResourceReference> textures;
    protected List<IModelQuad> quads;

    public SimpleModelPartBlock(ResourceReference all) {
        this.textures = new EnumMap<>(Direction.class);
        for (Direction dir : Direction.values()) {
            textures.put(dir, all);
        }
        generateQuads();
    }

    public SimpleModelPartBlock(EnumMap<Direction, ResourceReference> textures) {
        this.textures = new EnumMap<>(textures);
        generateQuads();
    }

    @Override
    public List<IModelQuad> getQuads() {
        return quads;
    }

    private void generateQuads() {
        quads = new ArrayList<>(6);
        for (Direction dir : Direction.values()) {
            Quad quad = new Quad(dir, textures.get(dir));
            quads.add(quad);
        }
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

        public Quad(Direction side, ResourceReference texture) {
            this.side = side;
            this.texture = texture;
            vertex = new Vect3d[4];
            uv = new Vect2d[4];
            for (int i = 0; i < 4; i++) {
                vertex[i] = new Vect3d(VERTEX[side.ordinal()][i]);
                uv[i] = new Vect2d(FULL_UV[side.ordinal()][i]);
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
            return side;
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