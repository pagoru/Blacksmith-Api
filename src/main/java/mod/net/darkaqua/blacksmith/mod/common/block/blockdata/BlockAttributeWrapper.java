package net.darkaqua.blacksmith.mod.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttribute;
import net.minecraft.block.properties.IProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cout970 on 23/02/2016.
 */
public class BlockAttributeWrapper<T extends Comparable<T>> implements IBlockAttribute<T> {

    private IProperty<T> property;

    public BlockAttributeWrapper(IProperty<T> property) {
        this.property = property;
    }

    public IProperty<T> getProperty() {
        return property;
    }

    @Override
    public String getAttributeName() {
        return property.getName();
    }

    @Override
    public String getValueName(T value) {
        return property.getName(value);
    }

    @Override
    public Class<T> getValueClass() {
        return property.getValueClass();
    }

    @Override
    public Set<T> getValidValues() {
        return new HashSet<>(property.getAllowedValues());
    }
}
