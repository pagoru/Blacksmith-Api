package net.darkaqua.blacksmith.mod.creativetab;

import net.darkaqua.blacksmith.api.creativetab.CreativeTabFactory;
import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by cout970 on 11/11/2015.
 */
public class BS_CreativeTabFactory extends CreativeTabFactory {

    private BS_CreativeTabFactory(){}

    public static void init(){
        INSTANCE = new BS_CreativeTabFactory();

        BLOCKS_TAB = MCInterface.fromCreativeTab(CreativeTabs.tabBlock);
        DECORATIONS_TAB = MCInterface.fromCreativeTab(CreativeTabs.tabDecorations);
        REDSTONE_TAB = MCInterface.fromCreativeTab(CreativeTabs.tabRedstone);
        TRANSPORT_TAB = MCInterface.fromCreativeTab(CreativeTabs.tabTransport);
        MISC_TAB = MCInterface.fromCreativeTab(CreativeTabs.tabMisc);
        SEARCH_TAB = MCInterface.fromCreativeTab(CreativeTabs.tabAllSearch);
        FOOD_TAB = MCInterface.fromCreativeTab(CreativeTabs.tabFood);
        TOOLS_TAB = MCInterface.fromCreativeTab(CreativeTabs.tabTools);
        COMBAT_TAB = MCInterface.fromCreativeTab(CreativeTabs.tabCombat);
        BREWING_TAB = MCInterface.fromCreativeTab(CreativeTabs.tabBrewing);
        MATERIALS_TAB = MCInterface.fromCreativeTab(CreativeTabs.tabMaterials);
        INVENTORY_TAB = MCInterface.fromCreativeTab(CreativeTabs.tabInventory);
    }

    @Override
    protected ICreativeTab create(String label, IItemStack tabIcon) {
        if(tabIcon == null)throw new NullPointerException("CreativeTabFactory cannot make a creative tab with a null tabIcon");
        BS_CreativeTab tab = new BS_CreativeTab(label, tabIcon);
        return MCInterface.fromCreativeTab(tab);
    }
}
