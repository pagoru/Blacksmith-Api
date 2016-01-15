package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItem;

import java.util.List;

/**
 * Created by cout970 on 22/12/2015.
 */
public interface IOreDictionary {

    int WILDCARD_VALUE = Short.MAX_VALUE;


    void registerOre(String name, IItemStack stack);

    void registerOre(String name, IItem item);

    void registerOre(String name, IBlock block);

    List<IItemStack> getOres(String name);

    List<String> getNames(IItemStack stack);

    List<String> getAllOres();
}
