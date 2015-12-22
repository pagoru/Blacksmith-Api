package net.darkaqua.blacksmith.mod.item;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItemBlock;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.util.MCInterface;
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
    public boolean placeBlock(IItemStack stack, IPlayer player, IBlockVariant variant, WorldRef ref, Direction side, Vect3d hit) {
        return item.placeBlockAt(MCInterface.toItemStack(stack), MCInterface.fromPlayer(player), MCInterface.toWorld(ref.getWorld()),
                MCInterface.toBlockPos(ref.getPosition()), MCInterface.toEnumFacing(side),(float)hit.getX(), (float)hit.getY(),
                (float)hit.getZ(), MCInterface.toIBlockState(variant));
    }
}
