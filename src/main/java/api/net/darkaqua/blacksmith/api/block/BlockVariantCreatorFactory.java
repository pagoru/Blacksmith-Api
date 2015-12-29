package net.darkaqua.blacksmith.api.block;

import net.minecraft.util.IStringSerializable;

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

    public static IIProperty createPropertyInteger(String name, int min, int max) {
        return INSTANCE.newPropertyInteger(name, min, max);
    }

    //I don't like to put IStringSerializable but I have to.
    public static <T extends Enum<T> & IStringSerializable> IIProperty createPropertyEnum(String name, Class<T> clazz) {
        return INSTANCE.newPropertyEnum(name, clazz);
    }

    public static IIProperty createPropertyBoolean(String name) {
        return INSTANCE.newPropertyBoolean(name);
    }

    public static IIProperty createPropertyDirection(String name) {
        return INSTANCE.newPropertyDirection(name);
    }


    protected abstract IBlockVariantCreator newBlockVariantCreator(IBlock block, IIProperty[] properties);

    protected abstract <T extends Comparable<T>> IIProperty newIProperty(String name, Map<String, T> values, Class<T> valuesClass);

    protected abstract IIProperty newPropertyInteger(String name, int min, int max);

    protected abstract <T extends Enum<T> & IStringSerializable> IIProperty newPropertyEnum(String name, Class<T> clazz);

    protected abstract IIProperty newPropertyBoolean(String name);

    protected abstract IIProperty newPropertyDirection(String name);
}
