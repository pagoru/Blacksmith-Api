package net.darkaqua.blacksmith.api.inventory;

import net.darkaqua.blacksmith.api.util.Direction;

/**
 * Created by cout970 on 19/12/2015.
 * Credits to rwtema for the idea:
 * https://github.com/rwtema/MinecraftForge/blob/deab3bf4a76302ff5c935684374a2cbf013cd276/src/main/java/net/minecraftforge/items/IItemHandler.java
 */
public interface IInventoryHandler {

    int getSlots(Direction side);

    IItemStack getStackInSlot(Direction side, int slot);

    IItemStack insertItemStack(Direction side, int slot, IItemStack stack, boolean simulated);

    IItemStack extractItemStack(Direction side, int slot, int amount, boolean simulated);
}
