package net.darkaqua.blacksmith.api.common.item;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.WorldRef;

/**
 * Created by cout970 on 21/12/2015.
 */
public interface IItemBlock extends IItem {

    IBlock getBlock();

    boolean placeBlock(IItemStack stack, IPlayer player, IBlockData variant, WorldRef ref, Direction side, Vect3d hit);
}
