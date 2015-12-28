package net.darkaqua.blacksmith.api.gui;

/**
 * Created by cout970 on 24/12/2015.
 */
public interface IGuiComponent {

    void renderBackground(IGui gui, int mouseX, int mouseY, float partialTicks);

    void renderForeground(IGui gui, int mouseX, int mouseY);

    void onMouseClick(IGui gui, int mouseX, int mouseY, int button);

    boolean onKeyPressed(IGui gui, int code, char character);
}
