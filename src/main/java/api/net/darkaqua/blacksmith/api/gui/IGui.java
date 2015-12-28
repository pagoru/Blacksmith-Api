package net.darkaqua.blacksmith.api.gui;

import net.darkaqua.blacksmith.api.container.IContainer;
import net.darkaqua.blacksmith.api.render.gui.IFontRenderer;
import net.darkaqua.blacksmith.api.util.Vect2i;

import java.util.List;

/**
 * Created by cout970 on 24/12/2015.
 */
public interface IGui {

    List<IGuiComponent> getComponents();

    void addComponent(IGuiComponent comp);

    void removeComponent(IGuiComponent comp);

    IContainer getContainer();

    IGuiDefinition getDefinition();

    IFontRenderer getFontRenderer();

    Vect2i getGuiSize();

    Vect2i getWindowSize();

    Vect2i getWindowStartingPoint();

    IGuiRenderer getGuiRenderer();

    boolean isAltKeyPressed();

    boolean isShiftKeyPressed();

    boolean isCtrlKeyPressed();
}
