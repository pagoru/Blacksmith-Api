package net.darkaqua.blacksmith.mod.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttributeValue;
import net.minecraft.block.properties.IProperty;

/**
 * Created by cout970 on 23/02/2016.
 */
public class VanillaBlockAttributeValue<T extends Comparable<T>> implements IBlockAttributeValue<T> {

    private IProperty<T> property;
    private T value;

    public VanillaBlockAttributeValue(IProperty<T> property, T value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public String getValueName() {
        return property.getName(value);
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
    public int compareTo(T o) {
        return value.compareTo(o);
    }
}
