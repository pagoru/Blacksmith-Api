package net.darkaqua.blacksmith.api.common.block.blockdata.defaults;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttributeValue;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockAttributeValueBoolean implements IBlockAttributeValue {

    public static final BlockAttributeValueBoolean TRUE = new BlockAttributeValueBoolean(true);
    public static final BlockAttributeValueBoolean FALSE = new BlockAttributeValueBoolean(false);
    public static final BlockAttributeValueBoolean[] VALUES = {FALSE, TRUE};

    public final Boolean value;

    private BlockAttributeValueBoolean(boolean value) {
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
    public IBlockAttributeValue getCanonicalValue() {
        return value ? TRUE : FALSE;
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
