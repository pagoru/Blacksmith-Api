package net.darkaqua.blacksmith.api.item;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.world.IWorld;

import java.util.List;

/**
 * Created by cout970 on 16/12/2015.
 */
public interface IItemDefinition {

    void onCreate(IItem parent);

    String getUnlocalizedName();
    String getUnlocalizedName(IItemStack stack);

    int getMaxStackSize(IItemStack stack);

    boolean hasSubtypes();

    IItemStack getContainerItemStack(IItemStack stack);

    ICreativeTab getCreativeTab();

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

}
