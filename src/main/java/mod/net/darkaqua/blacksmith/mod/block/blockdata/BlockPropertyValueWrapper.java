package net.darkaqua.blacksmith.mod.block.blockdata;

import net.darkaqua.blacksmith.api.block.blockdata.IBlockAttributeValue;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockPropertyValueWrapper implements IBlockAttributeValue {

    public final Comparable value;

    public BlockPropertyValueWrapper(Comparable value) {
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
        return this;
    }

    @Override
    public int compareTo(IBlockAttributeValue o) {
        return o instanceof BlockPropertyValueWrapper ? ((BlockPropertyValueWrapper) o).value.compareTo(value) : -1;
    }

    @Override
    public String toString(){
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockPropertyValueWrapper)) return false;

        BlockPropertyValueWrapper that = (BlockPropertyValueWrapper) o;

        return !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
