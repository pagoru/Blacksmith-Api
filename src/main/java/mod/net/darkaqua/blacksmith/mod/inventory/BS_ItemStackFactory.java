package net.darkaqua.blacksmith.mod.inventory;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.inventory.ItemStackFactory;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.item.ItemStack;

/**
 * Created by cout970 on 08/11/2015.
 */
public class BS_ItemStackFactory extends ItemStackFactory{

    public static void init() {
        INSTANCE = new BS_ItemStackFactory();
    }

    private BS_ItemStackFactory(){}

    @Override
    protected IItemStack newItemStack(IItem item, int amount, int metadata) {
        if(item == null) throw new NullPointerException("The ItemStackFactory cannot make an ItemStack with an null item");
        return MCInterface.fromItemStack(new ItemStack(MCInterface.toItem(item), amount, metadata));
    }

    @Override
    protected IItemStack newItemStack(IBlock block, int amount, int metadata) {
        if(block == null) throw new NullPointerException("The ItemStackFactory cannot make an ItemStack with an null item");
        return MCInterface.fromItemStack(new ItemStack(MCInterface.toBlock(block), amount, metadata));
    }
}