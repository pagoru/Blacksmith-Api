package net.darkaqua.blacksmith.api.block;

import java.util.Map;

/**
 * Created by cout970 on 28/11/2015.
 */
public abstract class BlockVariantCreatorFactory {

    protected static BlockVariantCreatorFactory INSTANCE;

    //TODO add parametrized returns

    public static IBlockVariantCreator createBlockVariantCreator(IBlock block, IIProperty... properties) {
        return INSTANCE.newBlockVariantCreator(block, properties);
    }

    public static <T extends Comparable<T>> IIProperty createProperty(String name, Map<String, T> values, Class<T> valuesClass) {
        return INSTANCE.newIProperty(name, values, valuesClass);
    }

    public static IIProperty<Integer> createPropertyInteger(String name, int min, int max) {
        return INSTANCE.newPropertyInteger(name, min, max);
    }

    public static <T extends Enum<T>> IIProperty<T> createPropertyEnum(String name, Class<T> clazz) {
        return INSTANCE.newPropertyEnum(name, clazz);
    }

    public static IIProperty<Boolean> createPropertyBoolean(String name) {
        return INSTANCE.newPropertyBoolean(name);
    }

    protected abstract IBlockVariantCreator newBlockVariantCreator(IBlock block, IIProperty[] properties);

    protected abstract <T extends Comparable<T>> IIProperty<T> newIProperty(String name, Map<String, T> values, Class<T> valuesClass);

    protected abstract IIProperty<Integer> newPropertyInteger(String name, int min, int max);

    protected abstract <T extends Enum<T>> IIProperty<T> newPropertyEnum(String name, Class<T> clazz);

    protected abstract IIProperty<Boolean> newPropertyBoolean(String name);
}
