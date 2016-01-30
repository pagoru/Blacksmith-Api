package net.darkaqua.blacksmith.mod.gui;

import net.darkaqua.blacksmith.api.gui.*;
import net.darkaqua.blacksmith.api.registry.ISoundHandler;
import net.darkaqua.blacksmith.api.registry.StaticAccess;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect2d;
import net.darkaqua.blacksmith.api.util.Vect2i;
import net.darkaqua.blacksmith.mod.registry.RenderManager;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.lwjgl.input.Mouse;

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
            c.renderBackground(this, new Vect2i(mouseX, mouseY), partialTicks);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        for (IGuiComponent c : components) {
            c.renderForeground(this, new Vect2i(x, y));
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int b) throws IOException {
        super.mouseClicked(x, y, b);
        for (IGuiComponent c : components) {
            c.onMouseClick(this, new Vect2i(x, y), IGuiComponent.MouseButton.fromID(b));
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
    public void drawRectangle(Vect2i start, Vect2i end, Color color) {
        drawRect(start.getX(), start.getY(), end.getX(), end.getY(), color.toInt());
    }

    @Override
    public void drawGradientRectangle(Vect2i start, Vect2i end, Color startColor, Color endColor) {
        drawGradientRect(start.getX(), start.getY(), end.getX(), end.getY(), startColor.toInt(), endColor.toInt());
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
    public void drawTexturedRectangle(Vect2i pos, Vect2i texturePos, Vect2i size) {
        drawTexturedModalRect(pos.getX(), pos.getY(), texturePos.getX(), texturePos.getY(), size.getX(), size.getY());
    }

    @Override
    public void drawRectangleWithCustomSizedTexture(Vect2i pos, Vect2i size, Vect2d textureUV, Vect2d textureSize) {
        drawModalRectWithCustomSizedTexture(pos.getX(), pos.getY(), (float)textureUV.getX(), (float)textureUV.getY(),
                size.getX(), size.getY(), (float)textureSize.getX(), (float)textureSize.getY());
    }

    @Override
    public void drawScaledCustomSizeRectangle(Vect2i pos, Vect2i size, Vect2d textureUV, Vect2i textureSize, Vect2d tileSize) {
        drawScaledCustomSizeModalRect(pos.getX(), pos.getY(), (float)textureUV.getX(), (float)textureUV.getY(),
                size.getX(), size.getY(), textureSize.getX(), textureSize.getY(), (float)tileSize.getX(), (float)tileSize.getY());
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
    public boolean isButtonDown(IGuiComponent.MouseButton button) {
        switch (button){
            case LEFT:
                return Mouse.isButtonDown(0);
            case RIGHT:
                return Mouse.isButtonDown(1);
            case MIDDLE:
                return Mouse.isButtonDown(2);
        }
        return false;
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

    @Override
    public ISoundHandler getSoundHandler() {
        return StaticAccess.GAME.getSoundHandler();
    }

}
