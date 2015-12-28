package com.cout970.testmod.gui;

import net.darkaqua.blacksmith.api.container.ContainerFactory;
import net.darkaqua.blacksmith.api.container.IContainer;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.gui.GuiFactory;
import net.darkaqua.blacksmith.api.gui.IGui;
import net.darkaqua.blacksmith.api.gui.IGuiCreationHandler;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 28/12/2015.
 */
public class GuiTestHandler implements IGuiCreationHandler {

    @Override
    public IContainer getServerContainer(IPlayer player, WorldRef ref, int id) {
        return ContainerFactory.createContainer(new ContainerTest());
    }

    @Override
    public IGui getClientGui(IPlayer player, WorldRef ref, int id) {
        return GuiFactory.createGui(new GuiTest(), ContainerFactory.createContainer(new ContainerTest()));
    }
}
