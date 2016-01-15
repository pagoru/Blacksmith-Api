package net.darkaqua.blacksmith.mod.block.blockstate;

import net.minecraft.block.properties.IProperty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cout970 on 29/12/2015.
 */
public class BS_Property<T extends Comparable<T>> implements IProperty<T> {

    private String name;
    private Map<T, String> values;
    private Class<T> clazz;

    public BS_Property(String name, Map<String, T> val, Class<T> valuesClass) {
        this.name = name;
        this.clazz = valuesClass;
        values = new HashMap<>();
        for (Map.Entry<String, T> e : val.entrySet()) {
            values.put(e.getValue(), e.getKey());
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<T> getAllowedValues() {
        return values.keySet();
    }

    @Override
    public Class<T> getValueClass() {
        return clazz;
    }

    @Override
    public String getName(T value) {
        return values.get(value);
    }
}
