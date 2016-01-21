package net.darkaqua.blacksmith.api.item.defaults;

import net.darkaqua.blacksmith.api.creativetab.CreativeTabFactory;
import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.item.IItemDefinition;

/**
 * Created by cout970 on 16/12/2015.
 */
public class DefaultItemDefinition implements IItemDefinition {

    protected String name;
    protected IItem parent;
    protected int maxDamage = 0;
    protected int maxStackSize = 64;
    protected boolean hasSubtypes = false;
    protected ICreativeTab creativeTab = CreativeTabFactory.DECORATIONS_TAB;

    public DefaultItemDefinition(String name) {
        this.name = name;
    }

    @Override
    public IItem getItem() {
        return parent;
    }

    @Override
    public void onCreate(IItem parent) {
        this.parent = parent;
    }

    @Override
    public String getUnlocalizedName() {
        return name;
    }

    @Override
    public int getMaxStackSize(IItemStack stack) {
        return maxStackSize;
    }

    @Override
    public boolean hasSubtypes() {
        return hasSubtypes;
    }

    @Override
    public ICreativeTab getCreativeTab() {
        return creativeTab;
    }

    @Override
    public int getMaxDamage() {
        return maxDamage;
    }
}
