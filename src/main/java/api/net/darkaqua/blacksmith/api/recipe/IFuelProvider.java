package net.darkaqua.blacksmith.api.recipe;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.annotations.Implementable;

/**
 * Created by cout970 on 23/12/2015.
 */
@Implementable
public interface IFuelProvider {

    int getBurnTime(IItemStack stack);
}
