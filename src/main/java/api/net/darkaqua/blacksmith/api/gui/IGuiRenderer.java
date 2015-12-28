package net.darkaqua.blacksmith.api.gui;

import net.darkaqua.blacksmith.api.render.gui.IFontRenderer;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.util.Vect2i;

import java.util.List;

/**
 * Created by cout970 on 25/12/2015.
 */
public interface IGuiRenderer {

    IFontRenderer getFontRenderer();

    void drawHoveringText(List<String> textLines, Vect2i pos);

    void drawHorizontalLine(int startX, int endX, int y, Color color);

    void drawVerticalLine(int x, int startY, int endY, Color color);

    void drawRectangle(int left, int top, int right, int bottom, Color color);

    void drawGradientRectangle(int left, int top, int right, int bottom, Color startColor, Color endColor);

    void drawCenteredString(String text, Vect2i pos, Color color);

    void drawString(String text, Vect2i pos, Color color);

    void drawTexturedRectangle(Vect2i pos, int textureX, int textureY, int width, int height);

    void drawRectangleWithCustomSizedTexture(Vect2i pos, float u, float v, int width, int height, float textureWidth, float textureHeight);

    void drawScaledCustomSizeRectangle(Vect2i pos, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight);
}
