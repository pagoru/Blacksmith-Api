package net.darkaqua.blacksmith.mod.client.creativetab;

import net.darkaqua.blacksmith.api.client.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by cout970 on 11/11/2015.
 */
public class CreativeTabWrapper implements ICreativeTab {

    private CreativeTabs tab;

    public CreativeTabWrapper(CreativeTabs tab) {
        this.tab = tab;
    }

    public CreativeTabs getCreativeTab() {
        return tab;
    }

    @Override
    public int getIndex() {
        return tab.getTabIndex();
    }

    @Override
    public String getLabel() {
        return tab.getTabLabel();
    }

    @Override
    public String getTranslatedLabel() {
        return tab.getTranslatedTabLabel();
    }

    @Override
    public IItemStack getItemStackIcon() {
        return MCInterface.fromItemStack(tab.getIconItemStack());
    }

    @Override
    public Object getInternalCreativeTab() {
        return tab;
    }
}
