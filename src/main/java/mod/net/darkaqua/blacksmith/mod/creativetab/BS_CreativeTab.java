package net.darkaqua.blacksmith.mod.creativetab;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by cout970 on 11/11/2015.
 */
public class BS_CreativeTab extends CreativeTabs {

    protected ItemStack icon;

    public BS_CreativeTab(String label, IItemStack icon) {
        super(label);
        this.icon = MCInterface.toItemStack(icon);
    }

    public ItemStack getIconItemStack() {
        return icon;
    }

    @Override
    public Item getTabIconItem() {
        return icon.getItem();
    }

    @Override
    public int getIconItemDamage() {
        return icon.getItemDamage();
    }
}
