package net.darkaqua.blacksmith.api.common.block.blockdata.defaults;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttributeValue;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockAttributeValueBoolean implements IBlockAttributeValue<Boolean> {

    public static final BlockAttributeValueBoolean TRUE = new BlockAttributeValueBoolean(true);
    public static final BlockAttributeValueBoolean FALSE = new BlockAttributeValueBoolean(false);
    public static final BlockAttributeValueBoolean[] VALUES = {FALSE, TRUE};

    public final Boolean value;

    private BlockAttributeValueBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public String getValueName() {
        return String.valueOf(value);
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public IBlockAttributeValue<Boolean> getCanonicalValue() {
        return value ? TRUE : FALSE;
    }

    @Override
    public String toString(){
        return getValueName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockAttributeValueBoolean)) return false;
        BlockAttributeValueBoolean that = (BlockAttributeValueBoolean) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
