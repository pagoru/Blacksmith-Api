package net.darkaqua.blacksmith.mod.block.blockstate;

import net.darkaqua.blacksmith.api.block.blockstate.IIProperty;
import net.minecraft.block.properties.IProperty;

import java.util.Collection;

/**
 * Created by cout970 on 28/11/2015.
 */
public class IPropertyWrapper implements IIProperty{

    private IProperty property;

    public IPropertyWrapper(IProperty property){
        this.property = property;
    }

    public IProperty getIProperty(){
        return property;
    }

    @Override
    public String getName() {
        return property.getName();
    }

    @Override
    public String getName(Comparable value) {
        return property.getName(value);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public Collection<Comparable<?>> getAllowedValues() {
        return property.getAllowedValues();
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public Class<? extends Comparable<?>> getValueClass() {
        return property.getValueClass();
    }

    @Override
    public Object getInternalProperty() {
        return property;
    }
}
