package net.darkaqua.blacksmith.mod.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttribute;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttributeValue;
import net.minecraft.block.properties.IProperty;

import java.util.*;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockAttribute<T extends IBlockAttributeValue<T>> implements IBlockAttribute<T>, IProperty<T> {

    private String name;
    private Set<T> values;

    public BlockAttribute(String name, Set<T> values) {
        this.name = name;
        this.values = new HashSet<>(values);
    }

    @Override
    public String getAttributeName() {
        return name;
    }

    @Override
    public Set<T> getValidValues() {
        return new HashSet<>(values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockAttribute)) return false;

        BlockAttribute that = (BlockAttribute) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(values != null ? !values.equals(that.values) : that.values != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (values != null ? values.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BlockAttribute{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public String getName() {
        return getAttributeName();
    }

    @Override
    public Collection<T> getAllowedValues() {
        return getValidValues();
    }

    @Override
    public Class<T> getValueClass() {
        return (Class<T>) values.stream().findFirst().get().getValue().getClass();
    }

    @Override
    public String getName(T value) {
        return values.contains(value) ? value.getValueName() : null;
    }
}
