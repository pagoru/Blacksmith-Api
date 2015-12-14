package net.darkaqua.blacksmith.api.render.model.default_models;

import net.darkaqua.blacksmith.api.render.model.IModelPart;
import net.darkaqua.blacksmith.api.render.model.IModelQuad;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vector2d;
import net.darkaqua.blacksmith.api.util.Vector3d;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by cout970 on 07/12/2015.
 */
public class SimpleModelPartCube implements IModelPart {

    private EnumMap<Direction, ResourceReference> textures;

    public SimpleModelPartCube(ResourceReference all) {
        this();
        for (Direction dir : Direction.values()) {
            textures.put(dir, all);
        }
    }

    public SimpleModelPartCube(EnumMap<Direction, ResourceReference> textures) {
        this.textures = new EnumMap<>(textures);
    }

    private SimpleModelPartCube() {
        textures = new EnumMap<>(Direction.class);
    }

    @Override
    public List<IModelQuad> getQuads() {
        List<IModelQuad> quads = new ArrayList<>(6);
        for (Direction dir : Direction.values()) {
            quads.add(new Quad(dir, textures.get(dir)));
        }
        return quads;
    }

    private static class Quad implements IModelQuad {

        private static final float[][][] FULL_UV = new float[][][]{
                {{0,1},{1,1},{1,0},{0,0}},//DOWN
                {{0,0},{0,1},{1,1},{1,0}},//UP
                {{1,1},{1,0},{0,0},{0,1}},//NORTH
                {{0,1},{1,1},{1,0},{0,0}},//SOUTH
                {{0,1},{1,1},{1,0},{0,0}},//WEST
                {{1,1},{1,0},{0,0},{0,1}},//EAST
                };

        private static final int[][][] VERTEX = new int[][][]{
                {{0,0,0},{1,0,0},{1,0,1},{0,0,1}},//DOWN
                {{0,1,0},{0,1,1},{1,1,1},{1,1,0}},//UP
                {{0,0,0},{0,1,0},{1,1,0},{1,0,0}},//NORTH
                {{0,0,1},{1,0,1},{1,1,1},{0,1,1}},//SOUTH
                {{0,0,0},{0,0,1},{0,1,1},{0,1,0}},//WEST
                {{1,0,0},{1,1,0},{1,1,1},{1,0,1}} //EAST
        };

        private Direction side;
        private ResourceReference texture;

        public Quad(Direction side, ResourceReference texture) {
            this.side = side;
            this.texture = texture;
        }

        @Override
        public Vector3d getVertex(QuadVertex pos) {
            return new Vector3d(VERTEX[side.ordinal()][pos.ordinal()]);
        }

        @Override
        public Vector2d getUV(QuadVertex pos) {
            return new Vector2d(FULL_UV[side.ordinal()][pos.ordinal()]);
        }

        @Override
        public Direction getNormal() {
            return side;
        }

        @Override
        public ResourceReference getTexture() {
            return texture;
        }
    }
}
