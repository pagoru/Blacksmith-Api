package net.darkaqua.blacksmith.mod.entity;

import net.darkaqua.blacksmith.api.command.IChatMessage;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.Vect3i;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by cout970 on 15/11/2015.
 */
public class EntityPlayerWrapper extends EntityLivingWrapper implements IPlayer {

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

    @Override
    public String getName() {
        return player.getDisplayNameString();
    }

    @Override
    public IChatMessage getDisplayName() {
        //TODO
        return null;
    }

    @Override
    public void sendChatMessage(IChatMessage msg) {
        //TODO
    }

    @Override
    public WorldRef getWorldRef() {
        return new WorldRef(MCInterface.fromWorld(player.worldObj), new Vect3i(player.posX, player.posY, player.posZ));
    }

    @Override
    public IEntity getEntity() {
        return this;
    }
}
