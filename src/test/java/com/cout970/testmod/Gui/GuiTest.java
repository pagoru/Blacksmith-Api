package com.cout970.testmod.gui;

import com.cout970.testmod.ModClass;
import net.darkaqua.blacksmith.api.gui.IGui;
import net.darkaqua.blacksmith.api.gui.defaults.BackgroundComponent;
import net.darkaqua.blacksmith.api.gui.defaults.DefaultGuiDefinition;
import net.darkaqua.blacksmith.api.util.ResourceReference;

/**
 * Created by cout970 on 28/12/2015.
 */
public class GuiTest extends DefaultGuiDefinition {

    @Override
    public void initGui(IGui parent) {
        parent.addComponent(new BackgroundComponent(new ResourceReference(ModClass.MOD_ID, "textures/gui/test_gui.png")));
    }
}
