package net.darkaqua.blacksmith.mod.gui;

import net.darkaqua.blacksmith.api.container.IContainer;
import net.darkaqua.blacksmith.api.gui.GuiFactory;
import net.darkaqua.blacksmith.api.gui.IGui;
import net.darkaqua.blacksmith.api.gui.IGuiDefinition;
import net.darkaqua.blacksmith.mod.container.BS_Container;
import net.darkaqua.blacksmith.mod.container.ContainerComponent;

/**
 * Created by cout970 on 28/12/2015.
 */
public class BS_GuiFactory extends GuiFactory {

    public static void init() {
        INSTANCE = new BS_GuiFactory();
    }

    @Override
    protected IGui newGui(IGuiDefinition def, IContainer cont) {
        return new BS_Gui(def, new BS_Container((ContainerComponent) cont));
    }
}
