package net.darkaqua.blacksmith.api.common.gui;

import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect2i;
import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;

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

    void receivedUpdate(int id, short data);

    boolean canInteractWith(IPlayer playerIn);
}
