package net.darkaqua.blacksmith.api.common.registry;

import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.gui.IGuiCreationHandler;
import net.darkaqua.blacksmith.api.common.modloader.IModIdentifier;
import net.darkaqua.blacksmith.api.common.util.WorldRef;

/**
 * Created by cout970 on 28/12/2015.
 */
public interface IGuiRegistry {

    void registerGuiCreationHandler(IGuiCreationHandler handler);

    void openGui(IPlayer player, WorldRef ref, int id, IModIdentifier modIdentifier);
}
