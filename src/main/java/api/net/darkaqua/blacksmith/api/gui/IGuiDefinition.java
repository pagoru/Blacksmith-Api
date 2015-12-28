package net.darkaqua.blacksmith.api.gui;

import net.darkaqua.blacksmith.api.util.Vect2i;

/**
 * Created by cout970 on 24/12/2015.
 */
public interface IGuiDefinition {

    void initGui(IGui parent);

    Vect2i getGuiSize();

    boolean doesPauseGame();

    void onClosed();
}
