package net.darkaqua.blacksmith.api.common.block.blockdata;

import java.util.List;

/**
 * Created by cout970 on 15/01/2016.
 */
public interface IBlockAttribute {

    String getName();

    List<IBlockAttributeValue> getValidValues();
}
