package net.darkaqua.blacksmith.api.gui;

import net.darkaqua.blacksmith.api.container.IContainer;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 24/12/2015.
 */
public interface IGuiCreationHandler {

    IContainer getServerContainer(IPlayer player, WorldRef ref, int id);

    IGui getClientGui(IPlayer player, WorldRef ref, int id);
}
