package net.darkaqua.blacksmith.api.item;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 21/12/2015.
 */
public interface IItemBlock extends IItem {

    IBlock getBlock();

    boolean placeBlock(IItemStack stack, IPlayer player, IBlockData variant, WorldRef ref, Direction side, Vect3d hit);
}
