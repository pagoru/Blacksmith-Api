package net.darkaqua.blacksmith.api.common.gui.defaults.components;

import net.darkaqua.blacksmith.api.common.gui.IGui;
import net.darkaqua.blacksmith.api.common.gui.IGuiComponent;
import net.darkaqua.blacksmith.api.common.gui.IGuiRenderer;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect2i;

import javax.annotation.Nonnull;

/**
 * Created by cout970 on 24/01/2016.
 */
public abstract class AbstractButton implements IGuiComponent {

    protected Vect2i pos;
    protected Vect2i size;
    protected int id;
    protected ButtonListener listener;

    public AbstractButton(Vect2i pos, Vect2i size, @Nonnull ButtonListener listener) {
        this.pos = pos;
        this.size = size;
        this.listener = listener;
    }

    @Override
    public void renderBackground(IGui gui, Vect2i mouse, float partialTicks) {
        renderButton(gui, gui.getGuiRenderer(), mouse);
    }

    @Override
    public void onMouseClick(IGui gui, Vect2i mouse, MouseButton button) {
        boolean sound = false;
        if (isInside(mouse, getPos(gui), size)) {
            sound = listener.onPress(this, mouse, button);
        }
        if (sound){
            emitPressSound(gui, mouse, button);
        }
    }

    @Override
    public boolean onKeyPressed(IGui gui, int code, char character) {
        return false;
    }

    protected Vect2i getPos(IGui gui){
        return pos.copy().add(gui.getGuiStartingPoint());
    }

    protected abstract void renderButton(IGui gui, IGuiRenderer guiRenderer, Vect2i mouse);

    protected abstract void emitPressSound(IGui gui, Vect2i mouse, MouseButton button);

    public static boolean isInside(Vect2i mouse, Vect2i pos, Vect2i size) {
        return isInside(mouse.getX(), mouse.getY(), pos.getX(), pos.getY(), size.getX(), size.getY());
    }

    public static boolean isInside(int mx, int my, int x, int y, int w, int h) {
        if (mx > x && mx < x + w) {
            if (my > y && my < y + h) {
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public AbstractButton setId(int id) {
        this.id = id;
        return this;
    }

    public interface ButtonListener {
        boolean onPress(AbstractButton button, Vect2i mouse, MouseButton mouseButton);
    }
}
