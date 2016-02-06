package net.darkaqua.blacksmith.api.block;

import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.intermod.IInterfaceIdentifier;
import net.darkaqua.blacksmith.api.intermod.InterfaceIdentifierHolder;
import net.darkaqua.blacksmith.api.util.Direction;

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
