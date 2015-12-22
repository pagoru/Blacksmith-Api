package net.darkaqua.blacksmith.mod.registry;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.registry.IOreDictionary;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 22/12/2015.
 */
public class OreDictionaryManager implements IOreDictionary {

    public static final OreDictionaryManager INSTANCE = new OreDictionaryManager();

    private OreDictionaryManager(){}

    @Override
    public void registerOre(String name, IItemStack stack) {
        OreDictionary.registerOre(name, MCInterface.toItemStack(stack));
    }

    @Override
    public void registerOre(String name, IItem item) {
        OreDictionary.registerOre(name, MCInterface.toItem(item));
    }

    @Override
    public void registerOre(String name, IBlock block) {
        OreDictionary.registerOre(name, MCInterface.toBlock(block));
    }

    @Override
    public List<IItemStack> getOres(String name) {
        List<ItemStack> list = OreDictionary.getOres(name);
        List<IItemStack> result = new LinkedList<>();
        for(ItemStack i : list){
            result.add(MCInterface.fromItemStack(i));
        }
        return result;
    }

    @Override
    public List<String> getNames(IItemStack item) {
        ItemStack stack = MCInterface.toItemStack(item);
        int[] ids = OreDictionary.getOreIDs(stack);
        List<String> list = Lists.newLinkedList();
        for(int id : ids) {
            list.add(OreDictionary.getOreName(id));
        }
        return list;
    }

    @Override
    public List<String> getAllOres() {
        return Lists.newArrayList(OreDictionary.getOreNames());
    }
}

