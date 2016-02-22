package net.darkaqua.blacksmith.api.common.entity;

import net.darkaqua.blacksmith.api.Game;
import net.darkaqua.blacksmith.api.common.command.ICommandExecutor;
import net.darkaqua.blacksmith.api.common.intermod.IInterfaceProvider;
import net.darkaqua.blacksmith.api.common.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.modloader.IModIdentifier;
import net.darkaqua.blacksmith.api.common.util.WorldRef;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3i;
import net.darkaqua.blacksmith.api.common.world.IWorld;

/**
 * Created by cout970 on 15/11/2015.
 */
public interface IPlayer extends ILivingEntity, ICommandExecutor, IInterfaceProvider {

    IInventoryHandler getInventory();

    IItemStack getSelectedItemStack();

    void setSelectedItemStack(IItemStack stack);

    boolean isSneaking();

    default void openGui(IModIdentifier mod, int guiId, IWorld world, Vect3i pos) {
        openGui(mod, guiId, new WorldRef(world, pos));
    }

    default void openGui(IModIdentifier mod, int guiId, WorldRef ref) {
        Game.getCommonHandler().getGuiRegistry().openGui(this, ref, guiId, mod);
    }
}
