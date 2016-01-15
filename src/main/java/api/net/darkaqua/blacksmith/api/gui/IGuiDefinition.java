package net.darkaqua.blacksmith.api.gui;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.util.Vect2i;
import net.darkaqua.blacksmith.api.util.annotations.Implementable;

/**
 * Created by cout970 on 24/12/2015.
 */
@Implementable
public interface IGuiDefinition {

    void initGui(IGui parent);

    Vect2i getGuiSize();

    boolean doesGuiPauseGame();

    void onGuiClosed();

    void initContainer(IContainer container);

    void onContainerClose(IPlayer player);

    void detectAndSendChanges();

    void updateProgressBar(int id, int data);

    boolean canInteractWith(IPlayer playerIn);
}
