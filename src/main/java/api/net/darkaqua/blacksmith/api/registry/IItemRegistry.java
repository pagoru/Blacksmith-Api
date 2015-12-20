package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.item.IItemDefinition;

import java.util.List;

/**
 * Created by cout970 on 19/12/2015.
 */
public interface IItemRegistry {

    IItem registerItemDefinition(IItemDefinition definition, String identifier);

    List<IItem> getRegisteredItems();

    List<IItemDefinition> getRegisteredItemDefinitions();

    IItem getItemFromDefinition(IItemDefinition def);

    IItem findItem(String domain, String name);
}
