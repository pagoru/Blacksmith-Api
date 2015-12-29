package net.darkaqua.blacksmith.api.block;

import java.util.Collection;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IIProperty<T extends Comparable<T>> {

    /**
     * The name of this property
     */
    String getName();

    /**
     * The name of the value of this property
     */
    String getName(T value);

    /**
     * A collection of all possible values of this property
     */
    Collection<T> getAllowedValues();

    /**
     * The class of the values from this property
     */
    Class<T> getValueClass();

    /**
     * The internal IProperty
     */
    Object getInternalProperty();
}
