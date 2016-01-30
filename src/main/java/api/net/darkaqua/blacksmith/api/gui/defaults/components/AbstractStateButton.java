package net.darkaqua.blacksmith.api.gui.defaults.components;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.gui.IGui;
import net.darkaqua.blacksmith.api.gui.IGuiRenderer;
import net.darkaqua.blacksmith.api.sound.SoundEffectFactory;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect2i;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

/**
 * Created by cout970 on 24/01/2016.
 */
public abstract class AbstractStateButton extends AbstractButton {

    protected String text;
    protected String tooltip;
    protected ResourceReference texture;
    protected ButtonState state;
    protected boolean pressed;

    public AbstractStateButton(Vect2i pos, Vect2i size, ResourceReference texture, String text, @Nonnull ButtonListener listener) {
        super(pos, size, listener);
        this.text = text;
        this.texture = texture;
    }

    public AbstractStateButton(Vect2i pos, Vect2i size, ResourceReference texture, String text, String tooltip, @Nonnull ButtonListener listener) {
        this(pos, size, texture, text, listener);
        this.tooltip = tooltip;
    }

    public AbstractStateButton(Vect2i pos, Vect2i size, ResourceReference texture, @Nonnull ButtonListener listener) {
        this(pos, size, texture, null, listener);
    }

    @Override
    protected void renderButton(IGui gui, IGuiRenderer rend, Vect2i mouse) {
        if (!gui.isButtonDown(MouseButton.LEFT) && !gui.isButtonDown(MouseButton.RIGHT)) {
            pressed = false;
            state = onButtonRelease();
        }
        state = onButtonHovered(isInside(mouse, getPos(gui), size));
        rend.bindTexture(texture);
        Vect2i uv = getUVFromState(state);
        rend.drawTexturedRectangle(getPos(gui), uv, size);
        String text = getText();
        if (text != null) {
            rend.drawCenteredString(text, getPos(gui).add(size.toVect2d().multiply(0.5).toVect2i()), getTextColor());
        }
    }

    @Override
    public void onMouseClick(IGui gui, Vect2i mouse, MouseButton button) {
       if (isInside(mouse, getPos(gui), size)) {
           pressed = button != MouseButton.MIDDLE;
           state = onButtonPress();
       }
        super.onMouseClick(gui, mouse, button);
    }

    @Override
    public void renderForeground(IGui gui, Vect2i mouse) {
        if (isInside(mouse, getPos(gui), size)) {
            List<String> toolTip = getButtonTooltip(gui, mouse);
            if (!toolTip.isEmpty()) {
                gui.getGuiRenderer().drawHoveringText(toolTip, mouse.sub(gui.getGuiStartingPoint()));
            }
        }
    }

    protected String getText() {
        return text;
    }

    protected List<String> getButtonTooltip(IGui gui, Vect2i mouse) {
        return tooltip == null ? Collections.EMPTY_LIST : Lists.newArrayList(tooltip);
    }

    @Override
    protected void emitPressSound(IGui gui, Vect2i mouse, MouseButton button) {
        gui.getSoundHandler().playSound(SoundEffectFactory.createSoundEffect(SoundEffectFactory.BUTTON_PRESS));
    }

    public Color getTextColor() {
        return new Color(0xFFFFFF);
    }

    protected abstract Vect2i getUVFromState(ButtonState state);

    protected abstract ButtonState onButtonPress();
    protected abstract ButtonState onButtonRelease();
    protected abstract ButtonState onButtonHovered(boolean hovered);

    public enum ButtonState {
        NORMAL(null),
        HOVER(NORMAL),
        DOWN(HOVER),
        DISABLED(null),
        DISABLED_HOVER(DISABLED),
        DISABLED_DOWN(DISABLED_HOVER),
        ACTIVE(null),
        ACTIVE_HOVER(ACTIVE),
        ACTIVE_DOWN(ACTIVE_HOVER),
        HIGHLIGHT(null);

        private ButtonState parent;

        ButtonState(ButtonState parent) {
            this.parent = parent;
        }

        public ButtonState getParent() {
            return parent;
        }
    }
}
