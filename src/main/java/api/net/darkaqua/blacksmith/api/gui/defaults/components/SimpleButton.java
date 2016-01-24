package net.darkaqua.blacksmith.api.gui.defaults.components;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.gui.IGui;
import net.darkaqua.blacksmith.api.gui.IGuiRenderer;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect2i;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

/**
 * Created by cout970 on 24/01/2016.
 */
public class SimpleButton extends AbstractButton {

    protected String text;
    protected String tooltip;
    protected ResourceReference texture;
    protected Vect2i uv;
    //TODO add sound

    public SimpleButton(Vect2i pos, Vect2i size, String text, ResourceReference texture, Vect2i uv, @Nonnull ButtonListener listener) {
        super(pos, size, listener);
        this.text = text;
        this.texture = texture;
        this.uv = uv;
    }

    public SimpleButton(Vect2i pos, Vect2i size, String text, ResourceReference texture, Vect2i uv, String tooltip, @Nonnull ButtonListener listener) {
        this(pos, size, text, texture, uv, listener);
        this.tooltip = tooltip;
    }

    public SimpleButton(Vect2i pos, Vect2i size, ResourceReference texture, Vect2i uv, @Nonnull ButtonListener listener) {
        this(pos, size, null, texture, uv, listener);
    }

    public SimpleButton(Vect2i pos, Vect2i size, ResourceReference texture, @Nonnull ButtonListener listener) {
        this(pos, size, null, texture, null, listener);
    }

    public SimpleButton(Vect2i pos, Vect2i size, ResourceReference texture, String text, @Nonnull ButtonListener listener) {
        this(pos, size, text, texture, null, listener);
    }

    @Override
    protected void renderButton(IGui gui, IGuiRenderer rend, Vect2i mouse) {
        rend.bindTexture(texture);
        if (uv == null) {
            uv = Vect2i.nullVector();
        }
        rend.drawTexturedRectangle(pos, uv, size);
        String text = getText();
        if (text != null) {
            rend.drawCenteredString(text, pos.copy().add(size.toVect2d().multiply(0.5).toVect2i()), getTextColor());
        }
    }

    protected String getText() {
        return text;
    }

    @Override
    protected List<String> getButtonTooltip(IGui gui, Vect2i mouse) {
        return tooltip == null ? Collections.EMPTY_LIST : Lists.newArrayList(tooltip);
    }

    @Override
    protected void emitPressSound(IGui gui, Vect2i mouse, MouseButton button) {
        //TODO add sound
    }

    public Color getTextColor() {
        return new Color(0xFFFFFF);
    }
}
