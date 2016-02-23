package net.darkaqua.blacksmith.api.common.block.blockdata;

import java.util.Set;

/**
 * Created by cout970 on 15/01/2016.
 */
public interface IBlockAttribute<T extends Comparable<T>> {

    String getAttributeName();

    String getValueName(T value);

    Class<T> getValueClass();

    Set<T> getValidValues();
}
