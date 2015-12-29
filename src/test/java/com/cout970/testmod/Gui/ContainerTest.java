package com.cout970.testmod.gui;

import net.darkaqua.blacksmith.api.container.IContainer;
import net.darkaqua.blacksmith.api.container.defaults.DefaultContainerDefinition;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.util.Vect2i;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 28/12/2015.
 */
public class ContainerTest extends DefaultContainerDefinition {

    public ContainerTest(IPlayer player, WorldRef ref) {
        super(player, ref);
    }

    @Override
    public void initContainer(IContainer container) {
        bindPlayerInventory(container, Vect2i.nullVector());
    }
}
