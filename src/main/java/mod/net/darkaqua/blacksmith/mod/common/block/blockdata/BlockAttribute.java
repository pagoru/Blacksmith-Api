package net.darkaqua.blacksmith.mod.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttribute;
import net.minecraft.block.properties.IProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockAttribute<T extends Comparable<T>> implements IBlockAttribute<T> {

    private IProperty<T> property;

    public BlockAttribute(String name, Class<T> clazz, Set<T> values) {
        property = new Property<>(this, name, new HashSet<>(values), clazz);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockAttribute)) return false;

        BlockAttribute<?> that = (BlockAttribute<?>) o;

        return !(property != null ? !property.equals(that.property) : that.property != null);

    }

    @Override
    public int hashCode() {
        return property != null ? property.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BlockAttribute{" +
                "property=" + property +
                '}';
    }

    public static class Property<T extends Comparable<T>> implements IProperty<T> {

        private BlockAttribute<T> attribute;
        private String name;
        private Set<T> values;
        private Class<T> clazz;

        public Property(BlockAttribute<T> attribute, String name, Set<T> values, Class<T> clazz) {
            this.attribute = attribute;
            this.name = name;
            this.values = values;
            this.clazz = clazz;
        }

        public BlockAttribute<T> getAttribute() {
            return attribute;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Collection<T> getAllowedValues() {
            return Collections.unmodifiableSet(values);
        }

        @Override
        public Class<T> getValueClass() {
            return clazz;
        }

        @Override
        public String getName(T value) {
            return values.contains(value) ? String.valueOf(value) : null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Property)) return false;

            Property<?> property = (Property<?>) o;

            if (name != null ? !name.equals(property.name) : property.name != null) return false;
            if (values != null ? !values.equals(property.values) : property.values != null) return false;
            return !(clazz != null ? !clazz.equals(property.clazz) : property.clazz != null);

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (values != null ? values.hashCode() : 0);
            result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Property{" +
                    "name='" + name + '\'' +
                    ", values=" + values +
                    ", clazz=" + clazz +
                    '}';
        }
    }
}
