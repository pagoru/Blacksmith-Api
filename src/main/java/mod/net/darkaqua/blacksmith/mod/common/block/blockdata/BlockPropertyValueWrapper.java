package net.darkaqua.blacksmith.mod.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttributeValue;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockPropertyValueWrapper<T extends Comparable<T>> implements IBlockAttributeValue<T> {

    private T value;

    public BlockPropertyValueWrapper(T value) {
        this.value = value;
    }

    @Override
    public String getValueName() {
        return String.valueOf(value);
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public IBlockAttributeValue<T> getCanonicalValue() {
        return this;
    }

    @Override
    public String toString(){
        return getValueName();
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
