package net.darkaqua.blacksmith.mod.common.item;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.item.IItemBlock;
import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.WorldRef;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.item.ItemBlock;

/**
 * Created by cout970 on 21/12/2015.
 */
public class ItemBlockWrapper extends ItemWrapper implements IItemBlock {

    private ItemBlock item;

    public ItemBlockWrapper(ItemBlock item) {
        super(item);
        this.item = item;
    }

    public ItemBlock getItemBlock() {
        return item;
    }

    @Override
    public IBlock getBlock() {
        return MCInterface.fromBlock(item.getBlock());
    }

    @Override
    public boolean placeBlock(IItemStack stack, IPlayer player, IBlockData variant, WorldRef ref, Direction side, Vect3d hit) {
        return item.placeBlockAt(MCInterface.toItemStack(stack), MCInterface.fromPlayer(player), MCInterface.toWorld(ref.getWorld()),
                MCInterface.toBlockPos(ref.getPosition()), MCInterface.toEnumFacing(side), (float) hit.getX(), (float) hit.getY(),
                (float) hit.getZ(), MCInterface.toBlockState(variant));
    }
}
