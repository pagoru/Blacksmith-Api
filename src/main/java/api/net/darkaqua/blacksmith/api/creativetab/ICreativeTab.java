package net.darkaqua.blacksmith.api.creativetab;

import net.darkaqua.blacksmith.api.inventory.IItemStack;

/**
 * Created by cout970 on 11/11/2015.
 */
public interface ICreativeTab {

    int getIndex();

    String getLabel();

    String getTranslatedLabel();

    IItemStack getItemStackIcon();

    Object getInternalCreativeTab();
}
