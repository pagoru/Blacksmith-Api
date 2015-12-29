package net.darkaqua.blacksmith.api.container.defaults;

import net.darkaqua.blacksmith.api.container.IContainer;
import net.darkaqua.blacksmith.api.container.IContainerDefinition;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.util.Vect2i;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 28/12/2015.
 */
public abstract class DefaultContainerDefinition implements IContainerDefinition {

    protected WorldRef ref;
    protected IPlayer player;

    public DefaultContainerDefinition(IPlayer player, WorldRef ref) {
        this.ref = ref;
        this.player = player;
    }

    @Override
    public void onClose(IPlayer player) {
    }

    @Override
    public void detectAndSendChanges() {
    }

    @Override
    public void updateProgressBar(int id, int data) {
    }

    @Override
    public boolean canInteractWith(IPlayer playerIn) {
        return true;
    }

    public void bindPlayerInventory(IContainer container, Vect2i pos) {
        for (int i = 0; i < 9; i++) {
            container.addSlot(new DefaultSlotDefinition(player.getInventory(), i, new Vect2i(8 + i * 18, 142).add(pos)));
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                container.addSlot(new DefaultSlotDefinition(player.getInventory(), j + i * 9 + 9, new Vect2i(8 + j * 18, 84 + i * 18).add(pos)));
            }
        }
    }
}
