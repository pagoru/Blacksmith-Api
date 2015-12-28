package net.darkaqua.blacksmith.api.gui.defaults;

import net.darkaqua.blacksmith.api.gui.IGuiDefinition;
import net.darkaqua.blacksmith.api.util.Vect2i;

/**
 * Created by cout970 on 25/12/2015.
 */
public abstract class DefaultGuiDefinition implements IGuiDefinition {

    @Override
    public Vect2i getGuiSize() {
        return new Vect2i(176, 166);
    }

    @Override
    public boolean doesPauseGame() {
        return false;
    }

    @Override
    public void onClosed() {}
}
