package net.darkaqua.blacksmith.api.item.properties;

import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.world.IWorld;

import java.util.List;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface IItemProperties {

    IItem getItem();

    int getMaxStackSize(IItemStack stack);
    void setMaxStackSize(IItemStack stack, int size);

    int getMaxDamage(IItemStack stack);
    void setMaxDamage(IItemStack stack, int max);

    boolean hasSubtypes();
    void setSubtypes(boolean has);

    IItemStack getContainerItemStack(IItemStack stack);
    void setContainerItemStack(IItemStack item);

    String getUnlocalizedName(IItemStack stack);
    void setUnlocalizedName(String name);
    String getLocalizedName(IItemStack stack);

    ICreativeTab getCreativeTab();
    void setCreativeTab(ICreativeTab tab);

    boolean is3DItem();

    int getMetadata(IItemStack stack);
    int getDamage(IItemStack stack);

    boolean isDamageable();

    boolean shouldRotateWhenRenderingInHand();

    Color getColorFromItemStack(IItemStack stack, int renderPass);

    int getItemUseMaxDuration(IItemStack stack);

    boolean isTool(IItemStack stack);

    int getItemEnchantability(IItemStack stack);

    void getSubItems(IItem item, ICreativeTab tab, List subItems);

    boolean canItemModifyBlocks();

    boolean isRepairable(IItemStack toRepair, IItemStack repair);

    int getEntityLifespan(IItemStack itemStack, IWorld world);
}
