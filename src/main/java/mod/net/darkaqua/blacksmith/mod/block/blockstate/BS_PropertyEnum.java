package net.darkaqua.blacksmith.mod.block.blockstate;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import net.minecraft.block.properties.PropertyHelper;

import java.util.Collection;
import java.util.Map;

/**
 * Created by cout970 on 03/01/2016.
 */
public class BS_PropertyEnum<T extends Enum<T>> extends PropertyHelper<T> {

    private final ImmutableSet<T> allowedValues;
    private final Map<String, T> nameToValue = Maps.<String, T>newHashMap();

    protected BS_PropertyEnum(String name, Class<T> valueClass, Collection<T> allowedValues) {
        super(name, valueClass);
        this.allowedValues = ImmutableSet.copyOf(allowedValues);

        for (T t : allowedValues) {
            String s = t.name();

            if (nameToValue.containsKey(s)) {
                throw new IllegalArgumentException("Multiple values have the same name \'" + s + "\'");
            }

            nameToValue.put(s, t);
        }
    }

    public Collection<T> getAllowedValues() {
        return allowedValues;
    }

    public String getName(T value) {
        return value.name();
    }
}
