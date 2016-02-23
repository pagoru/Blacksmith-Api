package net.darkaqua.blacksmith.mod.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttribute;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttributeValue;
import net.minecraft.block.properties.IProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cout970 on 23/02/2016.
 */
public class VanillaBlockAttribute<T extends IBlockAttributeValue<T>> implements IBlockAttribute<T> {

    private IProperty<T> property;

    public VanillaBlockAttribute(IProperty<T> property) {
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
    public Set<T> getValidValues() {
        return new HashSet<>(property.getAllowedValues());
    }

    @Override
    public String toString() {
        return "VanillaBlockAttribute{" +
                "property=" + property +
                '}';
    }
}
