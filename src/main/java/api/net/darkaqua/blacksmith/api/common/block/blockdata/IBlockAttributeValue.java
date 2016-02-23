package net.darkaqua.blacksmith.api.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;

/**
 * Created by cout970 on 15/01/2016.
 */
@Implementable
public interface IBlockAttributeValue<T extends Comparable<T>> extends Comparable<T>{

    String getValueName();

    T getValue();

    IBlockAttributeValue<T> getCanonicalValue();

    default int compareTo(T o){
        return getValue().compareTo(o);
    }
}
