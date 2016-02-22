package net.darkaqua.blacksmith.api.common.gui.defaults.components;

import net.darkaqua.blacksmith.api.common.gui.IGui;
import net.darkaqua.blacksmith.api.common.gui.IGuiComponent;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect2i;

/**
 * Created by cout970 on 28/12/2015.
 */
public class BackgroundComponent implements IGuiComponent {

    protected ResourceReference texture;

    public BackgroundComponent(ResourceReference texture) {
        this.texture = texture;
    }

    public ResourceReference getTexture() {
        return texture;
    }

    @Override
    public void renderBackground(IGui gui, Vect2i mouse, float partialTicks) {
        gui.getGuiRenderer().bindTexture(texture);
        gui.getGuiRenderer().drawTexturedRectangle(gui.getGuiStartingPoint(), Vect2i.nullVector(), gui.getGuiSize());
    }

    @Override
    public void renderForeground(IGui gui, Vect2i mouse) {
    }

    @Override
    public void onMouseClick(IGui gui, Vect2i mouse, MouseButton button) {
    }

    @Override
    public boolean onKeyPressed(IGui gui, int code, char character) {
        return false;
    }
}
