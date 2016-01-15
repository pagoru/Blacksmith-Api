package net.darkaqua.blacksmith.api.block.blockdata.defaults;

import net.darkaqua.blacksmith.api.block.blockdata.IBlockAttributeValue;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockAttributeValueBoolean implements IBlockAttributeValue {

    public static final BlockAttributeValueBoolean[] VALUES = {
            new BlockAttributeValueBoolean(false), new BlockAttributeValueBoolean(true)};

    public final Boolean value;

    public BlockAttributeValueBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return ""+value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public int compareTo(IBlockAttributeValue o) {
        return o instanceof BlockAttributeValueBoolean ? ((BlockAttributeValueBoolean) o).value == value ? 0 : 1 : -1;
    }

    @Override
    public String toString(){
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockAttributeValueBoolean)) return false;

        BlockAttributeValueBoolean that = (BlockAttributeValueBoolean) o;

        return !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
