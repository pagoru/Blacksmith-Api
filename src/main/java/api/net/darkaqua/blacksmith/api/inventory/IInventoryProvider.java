package net.darkaqua.blacksmith.api.inventory;

/**
 * Created by cout970 on 20/12/2015.
 *
 * Add this interface to an ITileEntityDefinition to make it an Inventory
 */
public interface IInventoryProvider {

    IInventoryHandler getInventory();
}
