package net.darkaqua.blacksmith.mod.gui;

import net.darkaqua.blacksmith.api.gui.IGui;
import net.darkaqua.blacksmith.api.gui.IGuiComponent;
import net.darkaqua.blacksmith.api.gui.IGuiDefinition;
import net.darkaqua.blacksmith.api.gui.IGuiRenderer;
import net.darkaqua.blacksmith.api.gui.IFontRenderer;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect2i;
import net.darkaqua.blacksmith.mod.registry.RenderManager;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.gui.inventory.GuiContainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 25/12/2015.
 */
public class BS_Gui extends GuiContainer implements IGui, IGuiRenderer {

    private List<IGuiComponent> components = new LinkedList<>();
    private IGuiDefinition definition;

    public BS_Gui(IGuiDefinition def) {
        super(new BS_Container(def));
        definition = def;
        Vect2i guiSize = def.getGuiSize();
        xSize = guiSize.getX();
        ySize = guiSize.getY();
        def.initGui(this);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        for (IGuiComponent c : components) {
            c.renderBackground(this, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        for (IGuiComponent c : components) {
            c.renderForeground(this, x, y);
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int b) throws IOException {
        super.mouseClicked(x, y, b);
        for (IGuiComponent c : components) {
            c.onMouseClick(this, x, y, b);
        }
    }

    protected void keyTyped(char letter, int num) throws IOException {
        boolean block = false;
        for (IGuiComponent c : components) {
            block |= c.onKeyPressed(this, num, letter);
        }
        if (!block) {
            super.keyTyped(letter, num);
        }
    }

    @Override
    public List<IGuiComponent> getComponents() {
        return new ArrayList<>(components);
    }

    @Override
    public void addComponent(IGuiComponent comp) {
        components.add(comp);
    }

    @Override
    public void removeComponent(IGuiComponent comp) {
        components.remove(comp);
    }

    @Override
    public IGuiDefinition getDefinition() {
        return definition;
    }

    @Override
    public IFontRenderer getFontRenderer() {
        return MCInterface.fromFontRenderer(fontRendererObj);
    }

    @Override
    public void drawHoveringText(List<String> textLines, Vect2i pos) {
        drawHoveringText(textLines, pos.getX(), pos.getY());
    }

    @Override
    public void drawHorizontalLine(int startX, int endX, int y, Color color) {
        drawHorizontalLine(startX, endX, y, color.toInt());
    }

    @Override
    public void drawVerticalLine(int x, int startY, int endY, Color color) {
        drawVerticalLine(x, startY, endY, color.toInt());
    }

    @Override
    public void drawRectangle(int left, int top, int right, int bottom, Color color) {
        drawRect(left, top, right, bottom, color.toInt());
    }

    @Override
    public void drawGradientRectangle(int left, int top, int right, int bottom, Color startColor, Color endColor) {
        drawGradientRect(left, top, right, bottom, startColor.toInt(), endColor.toInt());
    }

    @Override
    public void drawCenteredString(String text, Vect2i pos, Color color) {
        drawCenteredString(fontRendererObj, text, pos.getX(), pos.getY(), color.toInt());
    }

    @Override
    public void drawString(String text, Vect2i pos, Color color) {
        drawString(fontRendererObj, text, pos.getX(), pos.getY(), color.toInt());
    }

    @Override
    public void drawTexturedRectangle(Vect2i pos, int textureX, int textureY, int width, int height) {
        drawTexturedModalRect(pos.getX(), pos.getY(), textureX, textureY, width, height);
    }

    @Override
    public void drawRectangleWithCustomSizedTexture(Vect2i pos, float u, float v, int width, int height, float textureWidth, float textureHeight) {
        drawModalRectWithCustomSizedTexture(pos.getX(), pos.getY(), u, v, width, height, textureWidth, textureHeight);
    }

    @Override
    public void drawScaledCustomSizeRectangle(Vect2i pos, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight) {
        drawScaledCustomSizeModalRect(pos.getX(), pos.getY(), u, v, uWidth, vHeight, width, height, tileWidth, tileHeight);
    }

    @Override
    public void bindTexture(ResourceReference texture) {
        RenderManager.INSTANCE.bindTexture(texture);
    }

    @Override
    public Vect2i getGuiSize() {
        return new Vect2i(xSize, ySize);
    }

    @Override
    public Vect2i getWindowSize() {
        return new Vect2i(width, height);
    }

    @Override
    public Vect2i getGuiStartingPoint() {
        return new Vect2i(guiLeft, guiTop);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return definition.doesGuiPauseGame();
    }

    @Override
    public boolean isAltKeyPressed() {
        return isAltKeyDown();
    }

    @Override
    public boolean isShiftKeyPressed() {
        return isShiftKeyDown();
    }

    @Override
    public boolean isCtrlKeyPressed() {
        return isCtrlKeyDown();
    }

    @Override
    public void onGuiClosed() {
        definition.onGuiClosed();
        super.onGuiClosed();
    }

    @Override
    public IGuiRenderer getGuiRenderer() {
        return this;
    }

}
