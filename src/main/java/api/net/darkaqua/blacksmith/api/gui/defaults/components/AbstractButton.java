package net.darkaqua.blacksmith.api.gui.defaults.components;

import net.darkaqua.blacksmith.api.gui.IGui;
import net.darkaqua.blacksmith.api.gui.IGuiComponent;
import net.darkaqua.blacksmith.api.gui.IGuiRenderer;
import net.darkaqua.blacksmith.api.util.Vect2i;

import javax.annotation.Nonnull;
import java.util.List;

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
    public void renderForeground(IGui gui, Vect2i mouse) {
        List<String> toolTip = getButtonTooltip(gui, mouse);
        if (!toolTip.isEmpty()){
            gui.getGuiRenderer().drawHoveringText(toolTip, mouse);
        }
    }

    @Override
    public void onMouseClick(IGui gui, Vect2i mouse, MouseButton button) {
        boolean sound = false;
        if (isInside(mouse, pos, size)) {
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

    protected abstract void renderButton(IGui gui, IGuiRenderer guiRenderer, Vect2i mouse);

    protected abstract List<String> getButtonTooltip(IGui gui, Vect2i mouse);

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
