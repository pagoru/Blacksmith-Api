package net.darkaqua.blacksmith.api.client.render.model.defaults;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.client.render.model.IModelPart;
import net.darkaqua.blacksmith.api.client.render.model.IModelQuad;
import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect2d;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 19/12/2015.
 */
public class ModelPartBlockSide implements IModelPart {

    protected ResourceReference texture;
    protected Set<Direction> sides;
    protected List<IModelQuad> quads;

    public ModelPartBlockSide(Direction dir, ResourceReference texture) {
        this.texture = texture;
        this.sides.add(dir);
        generateQuads();
    }

    public ModelPartBlockSide(List<Direction> dir, ResourceReference texture) {
        this.texture = texture;
        this.sides.addAll(dir);
        generateQuads();
    }

    public ModelPartBlockSide(Direction[] dir, ResourceReference texture) {
        this(Lists.newArrayList(dir), texture);
    }

    @Override
    public ResourceReference getTexture() {
        return texture;
    }

    @Override
    public boolean useShade() {
        return true;
    }

    @Override
    public List<IModelQuad> getQuads() {
        return quads;
    }

    private void generateQuads() {
        quads.addAll(sides.stream().map(Quad::new).collect(Collectors.toList()));
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
        private Vect3d[] vertex;
        private Vect2d[] uv;

        public Quad(Direction side) {
            this.side = side;
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
        public Direction getSide() {
            return side;
        }
    }
}