package net.darkaqua.blacksmith.api.common.block.blockdata;

import java.util.Set;

/**
 * Created by cout970 on 15/01/2016.
 */
public interface IBlockAttribute<T extends IBlockAttributeValue<T>> {

    String getAttributeName();

    Set<T> getValidValues();
}
