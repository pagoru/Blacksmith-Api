package net.darkaqua.blacksmith.api.block.variants.defaults;

import net.darkaqua.blacksmith.api.block.variants.IBlockAttributeValue;
import net.darkaqua.blacksmith.api.util.Direction;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockAttributeValueDirection implements IBlockAttributeValue {

    public static final BlockAttributeValueDirection[] VALUES = {
            new BlockAttributeValueDirection(Direction.DOWN),new BlockAttributeValueDirection(Direction.UP),
            new BlockAttributeValueDirection(Direction.NORTH),new BlockAttributeValueDirection(Direction.SOUTH),
            new BlockAttributeValueDirection(Direction.WEST),new BlockAttributeValueDirection(Direction.EAST)};

    public static final BlockAttributeValueDirection[] HORIZONTAL_VALUES = {
            new BlockAttributeValueDirection(Direction.NORTH),new BlockAttributeValueDirection(Direction.SOUTH),
            new BlockAttributeValueDirection(Direction.WEST),new BlockAttributeValueDirection(Direction.EAST)};

    protected Direction value;

    public BlockAttributeValueDirection(Direction value) {
        this.value = value;
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
}
