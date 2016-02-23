package net.darkaqua.blacksmith.mod.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttribute;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttributeValue;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.block.properties.IProperty;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 23/02/2016.
 */
public class BlockPropertyWrapper<T extends Comparable<T>, R extends IBlockAttributeValue<R>> implements IProperty<T> {

    private IBlockAttribute<R> attribute;

    public BlockPropertyWrapper(IBlockAttribute<R> attribute) {
        this.attribute = attribute;
    }

    public IBlockAttribute<R> getAttribute() {
        return attribute;
    }

    @Override
    public String getName() {
        return attribute.getAttributeName();
    }

    @Override
    public Collection<T> getAllowedValues() {
        List<T> list = new LinkedList<>();
        for (R r : attribute.getValidValues()){
            list.add(MCInterface.fromBlockAttributeValue(r));
        }
        return list;
    }

    @Override
    public Class<T> getValueClass() {
        R r = attribute.getValidValues().stream().findFirst().get();
        return (Class<T>) MCInterface.fromBlockAttributeValue(r).getClass();
    }

    @Override
    public String getName(T value) {
        return attribute.getValidValues().stream().filter(i -> MCInterface.fromBlockAttributeValue(i).equals(value)).findFirst().get().getValueName();
    }
}
