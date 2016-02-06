package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.gui.IGuiCreationHandler;
import net.darkaqua.blacksmith.api.modloader.IModIdentifier;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 28/12/2015.
 */
public interface IGuiRegistry {

    void registerGuiCreationHandler(IGuiCreationHandler handler);

    void openGui(IPlayer player, WorldRef ref, int id, IModIdentifier modIdentifier);
}
