package net.darkaqua.blacksmith.api.inventory;

/**
 * Created by cout970 on 20/12/2015.
 * <p>
 * Add this interface to an ITileEntityDefinition to make it an Inventory
 */
@Deprecated
//TODO redo this with the new IInterfaceProvider
public interface IInventoryProvider {

    IInventoryHandler getInventory();
}
