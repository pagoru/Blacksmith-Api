package net.darkaqua.blacksmith.mod.block.blockdata;

import net.darkaqua.blacksmith.api.block.blockdata.IBlockAttribute;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockAttributeValue;
import net.minecraft.block.properties.IProperty;

import java.util.*;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockAttribute implements IBlockAttribute, IProperty<IBlockAttributeValue> {

    private String name;
    private List<IBlockAttributeValue> values;

    public BlockAttribute(String name, List<IBlockAttributeValue> values) {
        this.name = name;
        this.values = new ArrayList<>(values);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<IBlockAttributeValue> getAllowedValues() {
        return new ArrayList<>(values);
    }

    @Override
    public Class<IBlockAttributeValue> getValueClass() {
        return IBlockAttributeValue.class;
    }

    @Override
    public String getName(IBlockAttributeValue value) {
        return value.getName();
    }

    @Override
    public List<IBlockAttributeValue> getValidValues() {
        return new ArrayList<>(values);
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
}
