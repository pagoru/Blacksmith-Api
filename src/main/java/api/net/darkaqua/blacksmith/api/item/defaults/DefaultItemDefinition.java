package net.darkaqua.blacksmith.api.item.defaults;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.creativetab.CreativeTabFactory;
import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.inventory.ItemStackFactory;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.item.IItemDefinition;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.world.IWorld;

import java.util.List;

/**
 * Created by cout970 on 16/12/2015.
 */
public class DefaultItemDefinition implements IItemDefinition {

    protected String name;
    protected IItem parent;

    public DefaultItemDefinition(String name) {
        this.name = name;
    }

    @Override
    public void onCreate(IItem parent) {
        this.parent = parent;
    }

    @Override
    public String getUnlocalizedName() {
        return name;
    }

    @Override
    public String getUnlocalizedName(IItemStack stack) {
        return name;
    }

    @Override
    public int getMaxStackSize(IItemStack stack) {
        return 64;
    }

    @Override
    public boolean hasSubtypes() {
        return false;
    }

    @Override
    public IItemStack getContainerItemStack(IItemStack stack) {
        return null;
    }

    @Override
    public ICreativeTab getCreativeTab() {
        return CreativeTabFactory.MISC_TAB;
    }

    @Override
    public boolean is3DItem() {
        return false;
    }

    @Override
    public int getMetadata(IItemStack stack) {
        return stack.getDamage();
    }

    @Override
    public int getDamage(IItemStack stack) {
        return stack.getDamage();
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean shouldRotateWhenRenderingInHand() {
        return false;
    }

    @Override
    public Color getColorFromItemStack(IItemStack stack, int renderPass) {
        return new Color(0xFFFFFF);
    }

    @Override
    public int getItemUseMaxDuration(IItemStack stack) {
        return 0;
    }

    @Override
    public boolean isTool(IItemStack stack) {
        return false;
    }

    @Override
    public int getItemEnchantability(IItemStack stack) {
        return 0;
    }

    @Override
    public void getSubItems(IItem item, ICreativeTab tab, List<IItemStack> subItems) {
        subItems.add(ItemStackFactory.create(parent));
    }

    @Override
    public boolean canItemModifyBlocks() {
        return false;
    }

    @Override
    public boolean isRepairable(IItemStack toRepair, IItemStack repair) {
        return false;
    }

    @Override
    public int getEntityLifespan(IItemStack itemStack, IWorld world) {
        return 6000;//5 minutes
    }

    @Override
    public boolean onItemUse(IItemStack stack, IPlayer player, WorldRef ref, Direction side, Vect3d hit) {
        return false;
    }

    @Override
    public float getStrengthVsBlock(IItemStack stack, IBlock block) {
        return 0;
    }

    @Override
    public IItemStack onItemRightClick(IItemStack stack, IWorld world, IPlayer player) {
        return stack;
    }

    @Override
    public IItemStack onPlayerEndsUsing(IItemStack stack, IWorld world, IPlayer player) {
        return stack;
    }

    @Override
    public void onPlayerStoppedUsing(IItemStack stack, IWorld world, IPlayer player, int timeLeft) {}

    @Override
    public boolean hitEntity(IItemStack stack, IEntity target, IEntity attacker) {
        return false;
    }

    @Override
    public boolean onBlockDestroyed(IItemStack stack, WorldRef ref, IBlock block, IEntity player) {
        return false;
    }

    @Override
    public boolean canHarvestBlock(IItemStack stack, IBlock block) {
        return false;
    }

    @Override
    public boolean canInteractWithEntity(IItemStack stack, IPlayer player, IEntity target) {
        return false;
    }

    @Override
    public void onUpdate(IItemStack stack, IWorld world, IEntity entity, int itemSlot, boolean isSelected) {}

    @Override
    public void onCreated(IItemStack stack, IWorld world, IPlayer player) {}

    @Override
    public void addInformation(IItemStack stack, IPlayer player, List tooltip, boolean advanced) {}
}
