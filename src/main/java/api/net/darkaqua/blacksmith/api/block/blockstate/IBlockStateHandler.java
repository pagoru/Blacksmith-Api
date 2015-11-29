package net.darkaqua.blacksmith.api.block.blockstate;

import net.minecraft.block.state.IBlockState;

/**
 * Created by cout970 on 27/11/2015.
 */
public interface IBlockStateHandler {

    IIBlockState getDefaultBlockState();

    IIBlockState getBlockStateFromMeta(int meta);

    int getMetaFromBlockState(IBlockState state);
}
