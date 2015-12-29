package net.darkaqua.blacksmith.api.block;

import java.util.List;

/**
 * Created by cout970 on 29/12/2015.
 */
public interface IBlockVariantCreator {

    List<IBlockVariant> getValidVariants();

    IBlockVariant getBaseVariant();

    IBlock getBlock();

    List<IIProperty> getProperties();
}
