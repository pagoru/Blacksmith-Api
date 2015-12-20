package net.darkaqua.blacksmith.mod.block.blockstate;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.block.IIProperty;
import net.darkaqua.blacksmith.mod.exceptions.BlacksmithInternalException;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by cout970 on 08/11/2015.
 */
public class IBlockStateWrapper implements IBlockVariant {

    private IBlockState state;

    public IBlockStateWrapper(IBlockState state){
        if(state == null)
            throw new BlacksmithInternalException("Invalid blockstate");
        this.state = state;
    }

    public IBlockState getIBlockState(){
        return state;
    }

    @Override
    public IBlock getBlock() {
        return MCInterface.fromBlock(state.getBlock());
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public Collection<IIProperty> getProperties() {
        LinkedList<IIProperty> list = new LinkedList<>();
        for(IProperty p : state.getPropertyNames()){
            list.add(MCInterface.fromIProperty(p));
        }
        return list;
    }

    @Override
    public Comparable<?> getValue(IIProperty property) {
        return state.getValue(MCInterface.toIProperty(property));
    }

    @Override
    public IBlockVariant withProperty(IIProperty property, Comparable<?> value) {
        return MCInterface.fromIBlockState(state.withProperty(MCInterface.toIProperty(property), value));
    }

    @Override
    public IBlockVariant cycleProperty(IIProperty property) {
        return MCInterface.fromIBlockState(state.cycleProperty(MCInterface.toIProperty(property)));
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public Map<IIProperty, Comparable<?>> getPropertyMap() {
        Map<IIProperty, Comparable<?>> properties = new HashMap<>();
        for(Map.Entry<IProperty, Comparable> entry : state.getProperties().entrySet()){
            properties.put(MCInterface.fromIProperty(entry.getKey()), entry.getValue());
        }
        return properties;
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
    public Object getInternalBlockState() {
        return state;
    }
}
