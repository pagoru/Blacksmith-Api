package net.darkaqua.blacksmith.mod.common.entity;

import net.darkaqua.blacksmith.api.common.command.IChatMessage;
import net.darkaqua.blacksmith.api.common.entity.IEntity;
import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.intermod.IInterfaceIdentifier;
import net.darkaqua.blacksmith.api.common.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3i;
import net.darkaqua.blacksmith.api.common.util.WorldRef;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
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

    public EntityPlayer getPlayer() {
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

    public boolean isSneaking() {
        return player.isSneaking();
    }

    @Override
    public boolean hasInterface(IInterfaceIdentifier identifier, Direction side) {
        return identifier == IInventoryHandler.IDENTIFIER;
    }

    @Override
    public Object getInterface(IInterfaceIdentifier identifier, Direction side) {
        return identifier == IInventoryHandler.IDENTIFIER ? getInventory() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityPlayerWrapper)) return false;

        EntityPlayerWrapper that = (EntityPlayerWrapper) o;

        return !(player != null ? !player.equals(that.player) : that.player != null);

    }

    @Override
    public int hashCode() {
        return player != null ? player.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EntityPlayerWrapper{" +
                "player=" + player +
                '}';
    }
}
