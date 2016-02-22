package net.darkaqua.blacksmith.mod.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttribute;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttributeValue;
import net.minecraft.block.properties.IProperty;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockPropertyWrapper implements IBlockAttribute {

    private IProperty property;

    public BlockPropertyWrapper(IProperty property) {
        this.property = property;
    }

    public IProperty getProperty() {
        return property;
    }

    @Override
    public String getName() {
        return property.toString();
    }

    @Override
    public List<IBlockAttributeValue> getValidValues() {
        Collection<? extends Comparable<?>> col =  property.getAllowedValues();
        return col.stream().map(BlockPropertyValueWrapper::new).collect(Collectors.toList());
    }
}
