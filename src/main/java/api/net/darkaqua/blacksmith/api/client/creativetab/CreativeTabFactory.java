package net.darkaqua.blacksmith.api.client.creativetab;

import net.darkaqua.blacksmith.api.common.inventory.IItemStack;

/**
 * Created by cout970 on 11/11/2015.
 */
public abstract class CreativeTabFactory {

    protected static CreativeTabFactory INSTANCE;

    public static ICreativeTab BLOCKS_TAB;
    public static ICreativeTab DECORATIONS_TAB;
    public static ICreativeTab REDSTONE_TAB;
    public static ICreativeTab TRANSPORT_TAB;
    public static ICreativeTab MISC_TAB;
    public static ICreativeTab SEARCH_TAB;
    public static ICreativeTab FOOD_TAB;
    public static ICreativeTab TOOLS_TAB;
    public static ICreativeTab COMBAT_TAB;
    public static ICreativeTab BREWING_TAB;
    public static ICreativeTab MATERIALS_TAB;
    public static ICreativeTab INVENTORY_TAB;

    public static ICreativeTab createCreativeTab(String label, IItemStack tabIcon) {
        return INSTANCE.newCreativeTab(label, tabIcon);
    }

    protected abstract ICreativeTab newCreativeTab(String label, IItemStack tabIcon);
}
