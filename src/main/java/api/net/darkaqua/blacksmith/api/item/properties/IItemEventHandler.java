package net.darkaqua.blacksmith.api.item.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.world.IWorld;

import java.util.List;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface IItemEventHandler {

    IItem getItem();

    boolean onItemUse(IItemStack stack, IPlayer player, WorldRef ref, Direction side, float hitX, float hitY, float hitZ);

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
