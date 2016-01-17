package net.darkaqua.blacksmith.api.entity;

import net.darkaqua.blacksmith.api.command.ICommandExecutor;
import net.darkaqua.blacksmith.api.intermod.IInterfaceProvider;
import net.darkaqua.blacksmith.api.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.registry.StaticAccess;
import net.darkaqua.blacksmith.api.util.Vect3i;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.world.IWorld;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface IPlayer extends ILivingEntity, ICommandExecutor, IInterfaceProvider {

    IInventoryHandler getInventory();

    IItemStack getSelectedItemStack();

    void setSelectedItemStack(IItemStack stack);

    boolean isSneaking();

    default void openGui(Object mod, int guiId, IWorld world, Vect3i pos) {
        openGui(mod, guiId, new WorldRef(world, pos));
    }

    default void openGui(Object mod, int guiId, WorldRef ref) {
        StaticAccess.GAME.getGuiRegistry().openGui(this, ref, guiId, mod);
    }
}
