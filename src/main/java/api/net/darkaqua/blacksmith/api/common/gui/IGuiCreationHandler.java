package net.darkaqua.blacksmith.api.common.gui;

import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.util.GameSide;
import net.darkaqua.blacksmith.api.common.util.WorldRef;
import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;

/**
 * Created by cout970 on 24/12/2015.
 */
@Implementable
public interface IGuiCreationHandler {

    IGuiDefinition getGuiDefinition(IPlayer player, WorldRef ref, int id, GameSide side);
}
