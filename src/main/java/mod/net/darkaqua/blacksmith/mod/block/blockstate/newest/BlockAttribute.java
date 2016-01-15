package net.darkaqua.blacksmith.mod.block.blockstate.newest;

import net.darkaqua.blacksmith.api.block.variants.IBlockAttribute;
import net.darkaqua.blacksmith.api.block.variants.IBlockAttributeValue;
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
}
