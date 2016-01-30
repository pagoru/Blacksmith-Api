package com.cout970.testmod.gui;

import com.cout970.testmod.ModClass;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.gui.IContainer;
import net.darkaqua.blacksmith.api.gui.IGui;
import net.darkaqua.blacksmith.api.gui.defaults.DefaultGuiDefinition;
import net.darkaqua.blacksmith.api.gui.defaults.components.BackgroundComponent;
import net.darkaqua.blacksmith.api.gui.defaults.components.ToggleButton;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect2i;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.util.Log;

/**
 * Created by cout970 on 28/12/2015.
 */
public class GuiTest extends DefaultGuiDefinition {

    public GuiTest(IPlayer player, WorldRef ref) {
        super(player, ref);
    }

    @Override
    public void initGui(IGui parent) {
        parent.addComponent(new BackgroundComponent(new ResourceReference(ModClass.MOD_ID, "textures/gui/test_gui.png")));
        parent.addComponent(new ToggleButton(Vect2i.nullVector(), new Vect2i(16, 16), new ResourceReference(ModClass.MOD_ID, "textures/gui/buttons.png"),
                null, "tooltip",
                (button, mouse, mouseButton) -> {
                    if (button instanceof ToggleButton) {
                        Log.debug("pressed state:" + ((ToggleButton) button).isActive());
                    }else{
                        Log.debug("pressed");
                    }
                    return true;
                }, new Vect2i(16,0)));
    }

    @Override
    public void initContainer(IContainer container) {
        bindPlayerInventory(container, Vect2i.nullVector());
    }
}
