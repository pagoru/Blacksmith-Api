package net.darkaqua.blacksmith.api.block.variants;

import java.util.List;

/**
 * Created by cout970 on 15/01/2016.
 */
public interface IBlockAttribute {

    String getName();

    List<IBlockAttributeValue> getValidValues();
}
