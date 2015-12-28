package net.darkaqua.blacksmith.api.container.defaults;

import net.darkaqua.blacksmith.api.container.IContainerDefinition;
import net.darkaqua.blacksmith.api.entity.IPlayer;

/**
 * Created by cout970 on 28/12/2015.
 */
public abstract class DefaultContainerDefinition implements IContainerDefinition {

    @Override
    public void onClose(IPlayer player) {}

    @Override
    public void detectAndSendChanges() {}

    @Override
    public void updateProgressBar(int id, int data) {}

    @Override
    public boolean canInteractWith(IPlayer playerIn) {
        return true;
    }
}
