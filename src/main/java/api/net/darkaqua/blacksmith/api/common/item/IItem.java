package net.darkaqua.blacksmith.api.common.item;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.client.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.common.entity.IEntity;
import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.WorldRef;
import net.darkaqua.blacksmith.api.common.world.IWorld;

import java.awt.*;
import java.util.List;

public interface IItem {

    String getUnlocalizedName();

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

    void getSubItems(IItem item, ICreativeTab tab, List<IItemStack> subItems);

    boolean canItemModifyBlocks();

    boolean isRepairable(IItemStack toRepair, IItemStack repair);

    int getEntityLifespan(IItemStack itemStack, IWorld world);

    boolean onItemUse(IItemStack stack, IPlayer player, WorldRef ref, Direction side, Vect3d hit);

    float getStrengthVsBlock(IItemStack stack, IBlock block);

    IItemStack onItemRightClick(IItemStack stack, IWorld world, IPlayer player);

    //Called when the player finishes using this Item
    IItemStack onPlayerEndsUsing(IItemStack stack, IWorld world, IPlayer player);

    void onPlayerStoppedUsing(IItemStack stack, IWorld world, IPlayer player, int timeLeft);

    boolean hitEntity(IItemStack stack, IEntity target, IEntity attacker);

    boolean onBlockDestroyed(IItemStack stack, WorldRef ref, IBlock block, IEntity player);

    boolean canHarvestBlock(IItemStack stack, IBlock block);

    boolean canInteractWithEntity(IItemStack stack, IPlayer player, IEntity target);

    void onUpdate(IItemStack stack, IWorld world, IEntity entity, int itemSlot, boolean isSelected);

    void onCreated(IItemStack stack, IWorld world, IPlayer player);

    void addInformation(IItemStack stack, IPlayer player, List tooltip, boolean advanced);

    /**
     * If this item was created with and IItemDefinition this will return the definition, otherwise this will return null
     */
    IItemDefinition getItemDefinition();

    Object getInternalItem();
}
