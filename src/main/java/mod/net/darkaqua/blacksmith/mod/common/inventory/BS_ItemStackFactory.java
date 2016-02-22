package net.darkaqua.blacksmith.mod.common.inventory;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.inventory.ItemStackFactory;
import net.darkaqua.blacksmith.api.common.item.IItem;
import net.darkaqua.blacksmith.api.common.storage.IDataCompound;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by cout970 on 08/11/2015.
 */
public class BS_ItemStackFactory extends ItemStackFactory {

    public static void init() {
        INSTANCE = new BS_ItemStackFactory();
    }

    private BS_ItemStackFactory() {
    }

    @Override
    protected IItemStack load(IDataCompound data) {
        return MCInterface.fromItemStack(ItemStack.loadItemStackFromNBT(MCInterface.toNBTCompound(data)));
    }

    @Override
    protected void save(IDataCompound data, IItemStack stack) {
        NBTTagCompound nbt = MCInterface.toNBTCompound(data);
        ItemStack item = MCInterface.toItemStack(stack);
        item.writeToNBT(nbt);
    }

    @Override
    protected IItemStack newItemStack(IItem item, int amount, int metadata) {
        if (item == null)
            throw new NullPointerException("The ItemStackFactory cannot make an ItemStack with an null item");
        return MCInterface.fromItemStack(new ItemStack(MCInterface.toItem(item), amount, metadata));
    }

    @Override
    protected IItemStack newItemStack(IBlock block, int amount, int metadata) {
        if (block == null)
            throw new NullPointerException("The ItemStackFactory cannot make an ItemStack with an null item");
        return MCInterface.fromItemStack(new ItemStack(MCInterface.toBlock(block), amount, metadata));
    }
}