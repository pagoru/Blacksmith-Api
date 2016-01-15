package net.darkaqua.blacksmith.api.block.variants;

import net.darkaqua.blacksmith.api.util.annotations.Implementable;

/**
 * Created by cout970 on 15/01/2016.
 */
@Implementable
public interface IBlockAttributeValue extends Comparable<IBlockAttributeValue> {

    String getName();

    Object getValue();
}
