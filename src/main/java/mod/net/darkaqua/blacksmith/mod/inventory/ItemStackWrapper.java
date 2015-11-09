package net.darkaqua.blacksmith.mod.inventory;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.minecraft.item.ItemStack;

/**
 * Created by cout970 on 08/11/2015.
 */
public class ItemStackWrapper implements IItemStack{

    private ItemStack stack;

    public ItemStackWrapper(ItemStack stack){
        this.stack = stack;
    }

    public ItemStack getItemStack(){
        return stack;
    }
}
