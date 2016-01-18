package net.darkaqua.blacksmith.mod.render.gui;

import net.darkaqua.blacksmith.api.gui.IFontRenderer;
import net.minecraft.client.gui.FontRenderer;

/**
 * Created by cout970 on 18/12/2015.
 */
public class FontRendererWrapper implements IFontRenderer {

    private FontRenderer font;

    public FontRendererWrapper(FontRenderer font) {
        this.font = font;
    }

    public FontRenderer getFontRenderer() {
        return font;
    }

    @Override
    public int drawStringWithShadow(String text, float x, float y, int color) {
        return font.drawStringWithShadow(text, x, y, color);
    }

    @Override
    public int drawString(String text, int x, int y, int color) {
        return font.drawString(text, x, y, color);
    }

    @Override
    public int getStringWidth(String text) {
        return font.getStringWidth(text);
    }

    @Override
    public int getCharWidth(char character) {
        return font.getCharWidth(character);
    }

    @Override
    public String trimStringToWidth(String text, int width) {
        return font.trimStringToWidth(text, width);
    }

    @Override
    public Object getInternalObject() {
        return font;
    }
}
