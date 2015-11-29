package net.darkaqua.blacksmith.mod.block.blockstate;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.blockstate.IIBlockState;
import net.darkaqua.blacksmith.api.block.blockstate.IIProperty;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cout970 on 08/11/2015.
 */
public class IBlockStateWrapper implements IIBlockState{

    private IBlockState state;

    public IBlockStateWrapper(IBlockState state){
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
    public Collection<String> getPropertyName() {
        return state.getPropertyNames();
    }

    @Override
    public Comparable<?> getValue(IIProperty property) {
        return state.getValue(MCInterface.toIProperty(property));
    }

    @Override
    public IIBlockState withProperty(IIProperty property, Comparable<?> value) {
        return MCInterface.fromIBlockState(state.withProperty(MCInterface.toIProperty(property), value));
    }

    @Override
    public IIBlockState cycleProperty(IIProperty property) {
        return MCInterface.fromIBlockState(state.cycleProperty(MCInterface.toIProperty(property)));
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public Map<IIProperty, Comparable<?>> getProperties() {
        Map<IIProperty, Comparable<?>> properties = new HashMap<>();
        for(Object e : state.getProperties().entrySet()){
            Map.Entry<IProperty, Comparable> entry = (Map.Entry<IProperty, Comparable>) e;
            properties.put(MCInterface.fromIProperty(entry.getKey()), entry.getValue());
        }
        return properties;
    }

    @Override
    public Object getInternalBlockState() {
        return state;
    }
}
