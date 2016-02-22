package net.darkaqua.blacksmith.api.common.block.blockdata.defaults;

import net.darkaqua.blacksmith.api.common.block.blockdata.BlockDataFactory;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttribute;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttributeValue;
import net.darkaqua.blacksmith.api.common.util.Direction;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockAttributeValueDirection implements IBlockAttributeValue {

    public static final BlockAttributeValueDirection DOWN = new BlockAttributeValueDirection(Direction.DOWN);
    public static final BlockAttributeValueDirection UP = new BlockAttributeValueDirection(Direction.UP);
    public static final BlockAttributeValueDirection NORTH = new BlockAttributeValueDirection(Direction.NORTH);
    public static final BlockAttributeValueDirection SOUTH = new BlockAttributeValueDirection(Direction.SOUTH);
    public static final BlockAttributeValueDirection WEST = new BlockAttributeValueDirection(Direction.WEST);
    public static final BlockAttributeValueDirection EAST = new BlockAttributeValueDirection(Direction.EAST);

    public static final BlockAttributeValueDirection[] VALUES = {DOWN, UP, NORTH, SOUTH, WEST, EAST};
    public static final BlockAttributeValueDirection[] HORIZONTAL_VALUES = {NORTH, SOUTH, WEST, EAST};

    public static IBlockAttribute HORIZONTAL_DIRECTION = BlockDataFactory.createBlockAttribute("horizontal_direction", HORIZONTAL_VALUES);
    public static IBlockAttribute DIRECTION = BlockDataFactory.createBlockAttribute("direction", VALUES);

    private Direction value;

    private BlockAttributeValueDirection(Direction value) {
        this.value = value;
    }

    public Direction getDirection(){
        return value;
    }

    @Override
    public String getName() {
        return value.name();
    }

    @Override
    public String toString(){
        return getName();
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public IBlockAttributeValue getCanonicalValue() {
        return VALUES[value.ordinal()];
    }

    @Override
    public int compareTo(IBlockAttributeValue o) {
        return o instanceof BlockAttributeValueDirection ? ((BlockAttributeValueDirection) o).value.compareTo(value) : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockAttributeValueDirection)) return false;

        BlockAttributeValueDirection that = (BlockAttributeValueDirection) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    public static BlockAttributeValueDirection fromDirection(Direction direction) {
        return VALUES[direction.ordinal()];
    }
}
