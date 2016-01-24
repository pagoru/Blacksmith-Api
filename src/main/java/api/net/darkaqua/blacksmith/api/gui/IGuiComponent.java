package net.darkaqua.blacksmith.api.gui;

import net.darkaqua.blacksmith.api.util.Vect2i;
import net.darkaqua.blacksmith.api.util.annotations.Implementable;

/**
 * Created by cout970 on 24/12/2015.
 */
@Implementable
public interface IGuiComponent {

    void renderBackground(IGui gui, Vect2i mouse, float partialTicks);

    void renderForeground(IGui gui, Vect2i mouse);

    void onMouseClick(IGui gui, Vect2i mouse, MouseButton button);

    boolean onKeyPressed(IGui gui, int code, char character);

    enum MouseButton {
        LEFT, MIDDLE, RIGHT;

        public static MouseButton fromID(int id) {
            switch (id){
                case 0:
                    return LEFT;
                case 1:
                    return RIGHT;
                case 2:
                    return MIDDLE;
            }
            return null;
        }
    }
}
