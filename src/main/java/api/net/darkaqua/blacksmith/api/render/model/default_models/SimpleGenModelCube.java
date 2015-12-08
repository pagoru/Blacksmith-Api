package net.darkaqua.blacksmith.api.render.model.default_models;

import net.darkaqua.blacksmith.api.render.ResourceReference;
import net.darkaqua.blacksmith.api.render.model.generated.IGenModel;
import net.darkaqua.blacksmith.api.render.model.generated.IGenQuad;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vector2d;
import net.darkaqua.blacksmith.api.util.Vector3d;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by cout970 on 07/12/2015.
 */
public class SimpleGenModelCube implements IGenModel {

    private EnumMap<Direction, ResourceReference> textures;

    public SimpleGenModelCube(ResourceReference all) {
        this();
        for (Direction dir : Direction.values()) {
            textures.put(dir, all);
        }
    }

    public SimpleGenModelCube(EnumMap<Direction, ResourceReference> textures) {
        this.textures = new EnumMap<>(textures);
    }

    private SimpleGenModelCube() {
        textures = new EnumMap<>(Direction.class);
    }

    @Override
    public List<ResourceReference> getTextures() {
        return new ArrayList<>(textures.values());
    }

    @Override
    public List<IGenQuad> getQuads() {
        List<IGenQuad> quads = new ArrayList<>(6);
        for (Direction dir : Direction.values()) {
            quads.add(new Quad(dir, textures.get(dir)));
        }
        return quads;
    }

    private static class Quad implements IGenQuad {

        private static final Vector2d[] FULL_UV = new Vector2d[]
                {new Vector2d(0, 0), new Vector2d(0, 16),
                new Vector2d(16, 16), new Vector2d(16, 0)};

        private static final int[][][] VERTEX = new int[][][]{
                {{0,0,0},{1,0,0},{1,0,1},{0,0,1}},//DOWN
                {{0,1,0},{0,1,1},{1,0,1},{1,1,0}},//UP
                {{0,0,0},{1,0,0},{1,1,0},{0,1,0}},//NORTH
                {{0,0,1},{0,1,1},{1,1,1},{1,0,1}},//SOUTH
                {{0,0,0},{0,1,0},{0,1,1},{0,0,1}},//WEST
                {{1,0,0},{1,0,1},{1,1,1},{1,1,0}} //EAST
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
            return FULL_UV[pos.ordinal()].copy();
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
