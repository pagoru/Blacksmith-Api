package net.darkaqua.blacksmith.api.gui;

/**
 * Created by cout970 on 16/12/2015.
 */
public interface IFontRenderer {

    int drawStringWithShadow(String text, float x, float y, int color);

    int drawString(String text, int x, int y, int color);

    int getStringWidth(String text);

    int getCharWidth(char character);

    String trimStringToWidth(String text, int width);

    Object getInternalObject();
}
