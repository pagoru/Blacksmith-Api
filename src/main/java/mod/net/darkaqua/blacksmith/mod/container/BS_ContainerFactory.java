package net.darkaqua.blacksmith.mod.container;

import net.darkaqua.blacksmith.api.container.ContainerFactory;
import net.darkaqua.blacksmith.api.container.IContainer;
import net.darkaqua.blacksmith.api.container.IContainerDefinition;

/**
 * Created by cout970 on 28/12/2015.
 */
public class BS_ContainerFactory extends ContainerFactory {

    private BS_ContainerFactory(){}

    public static void init(){
        INSTANCE = new BS_ContainerFactory();
    }

    @Override
    protected IContainer newContainer(IContainerDefinition def) {
        return new ContainerComponent(def);
    }
}
