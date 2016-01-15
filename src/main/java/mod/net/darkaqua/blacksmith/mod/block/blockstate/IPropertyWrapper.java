package net.darkaqua.blacksmith.mod.block.blockstate;

import net.darkaqua.blacksmith.api.block.IIProperty;
import net.minecraft.block.properties.IProperty;

import java.util.Collection;

/**
 * Created by cout970 on 28/11/2015.
 */
public class IPropertyWrapper<T extends Comparable<T>> implements IIProperty<T> {

    private IProperty<T> property;

    public IPropertyWrapper(IProperty<T> property) {
        this.property = property;
    }

    public IProperty<T> getIProperty() {
        return property;
    }

    @Override
    public String getName() {
        return property.getName();
    }

    @Override
    public String getName(T value) {
        return property.getName(value);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public Collection<T> getAllowedValues() {
        return property.getAllowedValues();
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public Class<T> getValueClass() {
        return property.getValueClass();
    }

    @Override
    public Object getInternalProperty() {
        return property;
    }
}
