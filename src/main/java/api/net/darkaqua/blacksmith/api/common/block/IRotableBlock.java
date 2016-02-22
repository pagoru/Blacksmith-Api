package net.darkaqua.blacksmith.api.common.block;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.intermod.IInterfaceIdentifier;
import net.darkaqua.blacksmith.api.common.intermod.InterfaceIdentifierHolder;
import net.darkaqua.blacksmith.api.common.util.Direction;

/**
 * Created by cout970 on 06/02/2016.
 */
public interface IRotableBlock {

    @InterfaceIdentifierHolder(IRotableBlock.class)
    IInterfaceIdentifier<IRotableBlock> IDENTIFIER = null;

    Direction[] getValidRotations();

    Direction getActualRotation(IBlockData data);

    IBlockData stepRotation(IBlockData data);

    IBlockData setRotation(Direction dir);
}
