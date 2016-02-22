package net.darkaqua.blacksmith.api.common.recipe;

import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;

/**
 * Created by cout970 on 23/12/2015.
 */
@Implementable
public interface IFuelProvider {

    int getBurnTime(IItemStack stack);
}
