package net.darkaqua.blacksmith.api.gui;

import net.darkaqua.blacksmith.api.util.annotations.Implementable;

/**
 * Created by cout970 on 24/12/2015.
 */
@Implementable
public interface IGuiComponent {

    void renderBackground(IGui gui, int mouseX, int mouseY, float partialTicks);

    void renderForeground(IGui gui, int mouseX, int mouseY);

    void onMouseClick(IGui gui, int mouseX, int mouseY, int button);

    boolean onKeyPressed(IGui gui, int code, char character);
}
