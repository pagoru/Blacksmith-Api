package net.darkaqua.blacksmith.api.item;

import net.darkaqua.blacksmith.api.item.properties.IItemEventHandler;
import net.darkaqua.blacksmith.api.item.properties.IItemProperties;

public interface IItem {

    IItemProperties getItemProperties();

    IItemEventHandler getItemEventHandler();

    Object getInternalItem();
}
