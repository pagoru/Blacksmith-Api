package net.darkaqua.blacksmith.mod.entity;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by cout970 on 15/11/2015.
 */
public class EntityPlayerWrapper extends EntityWrapper implements IPlayer {

    private EntityPlayer player;

    public EntityPlayerWrapper(EntityPlayer player) {
        super(player);
        this.player = player;
    }

    public EntityPlayer getPlayer(){
        return player;
    }

    @Override
    public IItemStack getSelectedItemStack() {
        return MCInterface.fromItemStack(player.getCurrentEquippedItem());
    }

    @Override
    public void setSelectedItemStack(IItemStack stack) {
        player.setCurrentItemOrArmor(0, MCInterface.toItemStack(stack));
    }

    @Override
    public IInventoryHandler getInventory() {
        return MCInterface.fromInventory(player.inventory);
    }
}
