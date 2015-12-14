package net.darkaqua.blacksmith.api.block;

import java.util.Collection;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IIProperty {

    /**
     * The name of this property
     */
    String getName();

    /**
     * The name of the value of this property
     */
    String getName(Comparable<?> value);

    /**
     * A collection of all possible values of this property
     */
    Collection<Comparable<?>> getAllowedValues();

    /**
     * The class of the values from this property
     */
    Class<? extends Comparable<?>> getValueClass();

    /**
     * The internal IProperty
     */
    Object getInternalProperty();
}
