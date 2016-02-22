package net.darkaqua.blacksmith.api.gui.defaults;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.gui.IContainer;
import net.darkaqua.blacksmith.api.gui.IGuiDefinition;
import net.darkaqua.blacksmith.api.util.Vect2i;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 25/12/2015.
 */
public abstract class DefaultGuiDefinition implements IGuiDefinition {

    protected WorldRef ref;
    protected IPlayer player;

    public DefaultGuiDefinition(IPlayer player, WorldRef ref) {
        this.ref = ref;
        this.player = player;
    }

    @Override
    public Vect2i getGuiSize() {
        return new Vect2i(176, 166);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void onGuiClosed() {
    }

    @Override
    public void onContainerClose(IPlayer player) {
    }

    @Override
    public void detectAndSendChanges() {}

    @Override
    public void receivedUpdate(int id, short data) {}

    @Override
    public boolean canInteractWith(IPlayer playerIn) {
        return true;
    }

    public void bindPlayerInventory(IContainer container){
        bindPlayerInventory(container, Vect2i.nullVector());
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
