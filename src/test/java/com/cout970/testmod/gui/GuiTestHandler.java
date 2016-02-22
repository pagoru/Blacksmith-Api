package com.cout970.testmod.gui;

import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.gui.IGuiCreationHandler;
import net.darkaqua.blacksmith.api.common.gui.IGuiDefinition;
import net.darkaqua.blacksmith.api.common.util.GameSide;
import net.darkaqua.blacksmith.api.common.util.WorldRef;

/**
 * Created by cout970 on 28/12/2015.
 */
public class GuiTestHandler implements IGuiCreationHandler {

    @Override
    public IGuiDefinition getGuiDefinition(IPlayer player, WorldRef ref, int id, GameSide side) {
        return new GuiTest(player, ref);
    }
}
