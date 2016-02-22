package net.darkaqua.blacksmith.api.common.gui.defaults.components;

import net.darkaqua.blacksmith.api.common.util.ResourceReference;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect2i;

import javax.annotation.Nonnull;
import java.util.function.Function;

/**
 * Created by cout970 on 30/01/2016.
 */
public class ToggleButton extends AbstractStateButton {

    protected Function<ButtonState, Vect2i> uvMapper;

    public ToggleButton(Vect2i pos, Vect2i size, ResourceReference texture, String text, @Nonnull ButtonListener listener, Function<ButtonState, Vect2i> uvMapper) {
        super(pos, size, texture, text, listener);
        this.uvMapper = uvMapper;
    }

    public ToggleButton(Vect2i pos, Vect2i size, ResourceReference texture, String text, String tooltip, @Nonnull ButtonListener listener, Function<ButtonState, Vect2i> uvMapper) {
        super(pos, size, texture, text, tooltip, listener);
        this.uvMapper = uvMapper;
    }

    public ToggleButton(Vect2i pos, Vect2i size, ResourceReference texture, @Nonnull ButtonListener listener, Function<ButtonState, Vect2i> uvMapper) {
        super(pos, size, texture, listener);
        this.uvMapper = uvMapper;
    }

    public ToggleButton(Vect2i pos, Vect2i size, ResourceReference texture, String text, String tooltip, @Nonnull ButtonListener listener, Vect2i offset) {
        super(pos, size, texture, text, tooltip, listener);
        final Vect2i offset2 = offset.copy();
        final Vect2i offset3 = offset.copy().add(0, size.getY());
        final Vect2i offset4 = offset.copy().add(0, size.getY() * 2);
        final Vect2i offset5 = offset.copy().add(0, size.getY() * 3);
        final Vect2i offset6 = offset.copy().add(0, size.getY() * 4);
        final Vect2i offset7 = offset.copy().add(0, size.getY() * 5);

        this.uvMapper = new Function<ButtonState, Vect2i>() {
            @Override
            public Vect2i apply(ButtonState buttonState) {
                if (buttonState == ButtonState.ACTIVE)
                    return offset2;
                if (buttonState == ButtonState.ACTIVE_HOVER)
                    return offset3;
                if (buttonState == ButtonState.ACTIVE_DOWN)
                    return offset4;
                if (buttonState == ButtonState.DISABLED)
                    return offset5;
                if (buttonState == ButtonState.DISABLED_HOVER)
                    return offset6;
                if (buttonState == ButtonState.DISABLED_DOWN)
                    return offset7;
                return offset2;
            }
        };
    }

    @Override
    protected Vect2i getUVFromState(ButtonState state) {
        return uvMapper.apply(state);
    }

    @Override
    protected ButtonState onButtonPress() {
        return isActive() ? ButtonState.ACTIVE_DOWN : ButtonState.DISABLED_DOWN;
    }

    @Override
    protected ButtonState onButtonRelease() {
        return state == ButtonState.DISABLED_DOWN ? ButtonState.ACTIVE : state == ButtonState.ACTIVE_DOWN ? ButtonState.DISABLED : isActive() ? ButtonState.ACTIVE : ButtonState.DISABLED;
    }

    @Override
    protected ButtonState onButtonHovered(boolean hovered) {
        if (hovered) {
            return state == ButtonState.ACTIVE ? ButtonState.ACTIVE_HOVER : state == ButtonState.DISABLED ? ButtonState.DISABLED_HOVER : state;
        } else {
            return state == ButtonState.ACTIVE_HOVER ? ButtonState.ACTIVE : state == ButtonState.DISABLED_HOVER ? ButtonState.DISABLED : state;
        }
    }

    public boolean isActive() {
        return state == ButtonState.ACTIVE || state == ButtonState.ACTIVE_HOVER || state == ButtonState.DISABLED_DOWN;
    }
}
