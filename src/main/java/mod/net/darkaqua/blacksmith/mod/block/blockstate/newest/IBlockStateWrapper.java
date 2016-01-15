package net.darkaqua.blacksmith.mod.block.blockstate.newest;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.variants.IBlockAttribute;
import net.darkaqua.blacksmith.api.block.variants.IBlockAttributeValue;
import net.darkaqua.blacksmith.api.block.variants.IBlockData;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.state.IBlockState;

import java.util.Set;
import java.util.stream.Collectors;

import static net.darkaqua.blacksmith.mod.util.MCInterface.fromBlockAttribute;

/**
 * Created by cout970 on 15/01/2016.
 */
public class IBlockStateWrapper implements IBlockData {

    private IBlockState state;

    public IBlockStateWrapper(IBlockState state) {
        this.state = state;
    }

    public IBlockState getBlockState() {
        return state;
    }

    @Override
    public IBlock getBlock() {
        return MCInterface.fromBlock(state.getBlock());
    }

    @Override
    public IBlockAttributeValue getValue(IBlockAttribute attr) {
        Comparable o = state.getValue(fromBlockAttribute(attr));
        if (o instanceof IBlockAttributeValue){
            return (IBlockAttributeValue) o;
        }
        return new BlockPropertyValueWrapper(o);
    }

    @Override
    public Set<IBlockAttribute> getAttributes() {
        return state.getPropertyNames().stream().map(MCInterface::toBlockAttribute).collect(Collectors.toSet());
    }

    @Override
    public IBlockData getCycleValue(IBlockAttribute attr) {
        return MCInterface.fromIBlockState(state.cycleProperty(fromBlockAttribute(attr)));
    }

    @Override
    public IBlockData setValue(IBlockAttribute attr, IBlockAttributeValue value) {
        for(IBlockAttributeValue val : attr.getValidValues()){
            if(val.equals(value)){
                value = val;
                break;
            }
        }
        return MCInterface.fromIBlockState(state.withProperty(fromBlockAttribute(attr), value));
    }

    @Override
    public Object getInternalBlockState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IBlockStateWrapper)) return false;

        IBlockStateWrapper that = (IBlockStateWrapper) o;

        return !(state != null ? !state.equals(that.state) : that.state != null);

    }

    @Override
    public int hashCode() {
        return state != null ? state.hashCode() : 0;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
