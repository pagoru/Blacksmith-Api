package net.darkaqua.blacksmith.api.gui.defaults;

import net.darkaqua.blacksmith.api.gui.IGui;
import net.darkaqua.blacksmith.api.gui.IGuiComponent;
import net.darkaqua.blacksmith.api.util.ResourceReference;

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
    public void renderBackground(IGui gui, int mouseX, int mouseY, float partialTicks) {
        gui.getGuiRenderer().bindTexture(texture);
        gui.getGuiRenderer().drawTexturedRectangle(gui.getGuiStartingPoint(), 0, 0, gui.getGuiSize().getX(), gui.getGuiSize().getY());
    }

    @Override
    public void renderForeground(IGui gui, int mouseX, int mouseY) {}

    @Override
    public void onMouseClick(IGui gui, int mouseX, int mouseY, int button) {}

    @Override
    public boolean onKeyPressed(IGui gui, int code, char character) {
        return false;
    }
}
