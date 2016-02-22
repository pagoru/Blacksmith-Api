package net.darkaqua.blacksmith.api.common.item;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.client.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.common.entity.IEntity;
import net.darkaqua.blacksmith.api.common.entity.ILivingEntity;
import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.inventory.ItemStackFactory;
import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.WorldRef;
import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;
import net.darkaqua.blacksmith.api.common.world.IWorld;

import java.awt.*;
import java.util.List;

/**
 * Created by cout970 on 16/12/2015.
 */
@Implementable
public interface IItemDefinition {

    IItem getItem();

    void onCreate(IItem parent);

    String getUnlocalizedName();

    default String getUnlocalizedName(IItemStack stack) {
        return getUnlocalizedName();
    }

    default int getMaxStackSize(IItemStack stack) {
        return 64;
    }

    default boolean hasSubtypes() {
        return false;
    }

    default IItemStack getContainerItemStack(IItemStack stack) {
        return null;
    }

    ICreativeTab getCreativeTab();

    default boolean is3DItem() {
        return false;
    }

    default int getMetadata(IItemStack stack) {
        return stack.getDamage();
    }

    default int getDamage(IItemStack stack) {
        return stack.getDamage();
    }

    default int getMaxDamage() {
        return 0;
    }

    default int getMaxDamage(IItemStack stack){
        return getMaxDamage();
    }

    default boolean isDamageable() {
        return getMaxDamage() > 0;
    }

    default boolean shouldRotateWhenRenderingInHand() {
        return false;
    }

    default Color getColorFromItemStack(IItemStack stack, int renderPass) {
        return new Color(0xFFFFFF);
    }

    default int getItemUseMaxDuration(IItemStack stack) {
        return 0;
    }

    default boolean isTool(IItemStack stack) {
        return false;
    }

    default int getItemEnchantability(IItemStack stack) {
        return 0;
    }

    default void getSubItems(IItem item, ICreativeTab tab, List<IItemStack> subItems) {
        subItems.add(ItemStackFactory.createItemStack(getItem()));
    }

    default boolean canItemModifyBlocks() {
        return false;
    }

    default boolean isRepairable(IItemStack toRepair, IItemStack repaired) {
        return false;
    }

    default int getEntityLifespan(IItemStack itemStack, IWorld world) {
        return 6000;//5 minutes
    }

    default boolean onItemUse(IItemStack stack, IPlayer player, WorldRef ref, Direction side, Vect3d hit) {
        return false;
    }

    default float getStrengthVsBlock(IItemStack stack, IBlockData block) {
        return 1f;
    }

    default IItemStack onItemRightClick(IItemStack stack, IWorld world, IPlayer player) {
        return stack;
    }

    //Called when the player finishes using this Item
    default IItemStack onPlayerEndsUsing(IItemStack stack, IWorld world, IPlayer player) {
        return stack;
    }

    default void onPlayerStoppedUsing(IItemStack stack, IWorld world, IPlayer player, int timeLeft) {
    }

    default boolean hitEntity(IItemStack stack, ILivingEntity target, ILivingEntity attacker) {
        return false;
    }

    default boolean onBlockDestroyed(IItemStack stack, WorldRef ref, ILivingEntity player) {
        return false;
    }

    default boolean canHarvestBlock(IItemStack stack, IBlockData block) {
        return true;
    }

    default boolean canInteractWithEntity(IItemStack stack, IPlayer player, ILivingEntity target) {
        return true;
    }

    default void onUpdate(IItemStack stack, IWorld world, IEntity entity, int itemSlot, boolean isSelected) {
    }

    default void onCreated(IItemStack stack, IWorld world, IPlayer player) {
    }

    default void addInformation(IItemStack stack, IPlayer player, List<String> tooltip, boolean advanced) {
    }

    default boolean isDamaged(IItemStack stack){
        return stack.getDamage() > 0;
    }

    default double getDurabilityBarValue(IItemStack stack){
        return (double) stack.getDamage() / stack.getMaxDamage();
    }


}
