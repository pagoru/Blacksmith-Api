package net.darkaqua.blacksmith.api.entity;

import net.darkaqua.blacksmith.api.inventory.IInventoryProvider;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.Vect3i;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.world.IWorld;

public interface IPlayer extends ILivingEntity, IInventoryProvider {

    IItemStack getSelectedItemStack();

    void setSelectedItemStack(IItemStack stack);

    boolean isSneaking();

    void openGui(Object mod, int guiId, IWorld world, int x, int y, int z);

    default void openGui(Object mod, int guiId, IWorld world, Vect3i pos) {
        openGui(mod, guiId, world, pos.getX(), pos.getY(), pos.getZ());
    }

    default void openGui(Object mod, int guiId, WorldRef ref) {
        openGui(mod, guiId, ref.getWorld(), ref.getPosition());
    }
}
