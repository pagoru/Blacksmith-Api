package net.darkaqua.blacksmith.api.gui;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.util.GameSide;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 24/12/2015.
 */
public interface IGuiCreationHandler {

    IGuiDefinition getGuiDefinition(IPlayer player, WorldRef ref, int id, GameSide side);
}
