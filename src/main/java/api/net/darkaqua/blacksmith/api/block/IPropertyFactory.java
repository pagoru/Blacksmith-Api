package net.darkaqua.blacksmith.api.block;

import java.util.Map;

/**
 * Created by cout970 on 28/11/2015.
 */
public abstract class IPropertyFactory {

    protected static IPropertyFactory INSTANCE;

    public static <T> IIProperty createProperty(String name, Map<String, Comparable<T>> values, Class<T> valuesClass){
        return INSTANCE.newIProperty(name, values, valuesClass);
    }

    public static IIProperty createPropertyInteger(String name, int min, int max){
        return INSTANCE.newPropertyInteger(name, min, max);
    }

    public static IIProperty createPropertyEnum(String name, Class<? extends Enum> clazz){
        return INSTANCE.newPropertyEnum(name, clazz);
    }

    public static IIProperty createPropertyBoolean(String name){
        return INSTANCE.newPropertyBoolean(name);
    }

    public static IIProperty createPropertyDirection(String name){
        return INSTANCE.newPropertyDirection(name);
    }

    protected abstract IBlockVariant newBlockState(IBlock block, IIProperty[] properties);

    protected abstract <T> IIProperty newIProperty(String name, Map<String, Comparable<T>> values, Class<T> valuesClass);
    protected abstract IIProperty newPropertyInteger(String name, int min, int max);
    protected abstract IIProperty newPropertyEnum(String name, Class<? extends Enum> clazz);
    protected abstract IIProperty newPropertyBoolean(String name);
    protected abstract IIProperty newPropertyDirection(String name);
}
