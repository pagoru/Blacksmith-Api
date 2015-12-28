package net.darkaqua.blacksmith.api.container;

import net.darkaqua.blacksmith.api.entity.IPlayer;

/**
 * Created by cout970 on 24/12/2015.
 */
public interface IContainerDefinition {

    void initContainer(IContainer container);

    void onClose(IPlayer player);

    void detectAndSendChanges();

    void updateProgressBar(int id, int data);

    boolean canInteractWith(IPlayer playerIn);
}
