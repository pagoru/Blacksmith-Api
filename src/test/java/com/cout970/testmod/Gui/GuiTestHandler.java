package com.cout970.testmod.gui;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.gui.IGuiCreationHandler;
import net.darkaqua.blacksmith.api.gui.IGuiDefinition;
import net.darkaqua.blacksmith.api.util.GameSide;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 28/12/2015.
 */
public class GuiTestHandler implements IGuiCreationHandler {

    @Override
    public IGuiDefinition getGuiDefinition(IPlayer player, WorldRef ref, int id, GameSide side) {
        return new GuiTest(player, ref);
    }
}
