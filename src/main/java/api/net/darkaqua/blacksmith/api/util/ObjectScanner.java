package net.darkaqua.blacksmith.api.util;

/**
 * Created by cout970 on 13/11/2015.
 */
public abstract class ObjectScanner {

    protected static ObjectScanner INSTANCE;

    public static <T> T findByClass(Object toScan, Class<T> clazz){
        return INSTANCE.find(toScan, clazz);
    }

    protected abstract <T> T find(Object toScan, Class<T> clazz);
}
