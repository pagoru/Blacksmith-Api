package net.darkaqua.blacksmith.api.common.gui.defaults.components;

import net.darkaqua.blacksmith.api.common.util.ResourceReference;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect2i;

import javax.annotation.Nonnull;
import java.util.function.Function;

/**
 * Created by cout970 on 30/01/2016.
 */
public class SimpleButton extends AbstractStateButton {

    protected Function<ButtonState, Vect2i> uvMapper;

    public SimpleButton(Vect2i pos, Vect2i size, ResourceReference texture, String text, @Nonnull ButtonListener listener, Function<ButtonState, Vect2i> uvMapper) {
        super(pos, size, texture, text, listener);
        this.uvMapper = uvMapper;
    }

    public SimpleButton(Vect2i pos, Vect2i size, ResourceReference texture, String text, String tooltip, @Nonnull ButtonListener listener, Function<ButtonState, Vect2i> uvMapper) {
        super(pos, size, texture, text, tooltip, listener);
        this.uvMapper = uvMapper;
    }

    public SimpleButton(Vect2i pos, Vect2i size, ResourceReference texture, @Nonnull ButtonListener listener, Function<ButtonState, Vect2i> uvMapper) {
        super(pos, size, texture, listener);
        this.uvMapper = uvMapper;
    }

    public SimpleButton(Vect2i pos, Vect2i size, ResourceReference texture, @Nonnull ButtonListener listener, Vect2i offset) {
        this(pos, size, texture, null, null, listener, offset);
    }

    public SimpleButton(Vect2i pos, Vect2i size, ResourceReference texture, String text, String tooltip, @Nonnull ButtonListener listener, Vect2i offset) {
        super(pos, size, texture, text, tooltip, listener);
        Vect2i offset2 = offset.copy();
        Vect2i offset3 = offset.copy().add(0, size.getY());
        Vect2i offset4 = offset.copy().add(0, size.getY()*2);
        this.uvMapper = buttonState -> {
            switch (buttonState){
                case NORMAL:
                    return offset2;
                case HOVER:
                    return offset3;
                case DOWN:
                    return offset4;
            }
            return offset2;
        };
    }

    @Override
    protected Vect2i getUVFromState(ButtonState state) {
        return uvMapper.apply(state);
    }

    @Override
    protected ButtonState onButtonPress() {
        return ButtonState.DOWN;
    }

    @Override
    protected ButtonState onButtonRelease() {
        return ButtonState.NORMAL;
    }

    @Override
    protected ButtonState onButtonHovered(boolean hovered) {
        return pressed ? ButtonState.DOWN : hovered ? ButtonState.HOVER : ButtonState.NORMAL;
    }
}
