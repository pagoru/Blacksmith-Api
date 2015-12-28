package net.darkaqua.blacksmith.api.container;

/**
 * Created by cout970 on 25/12/2015.
 */
public abstract class ContainerFactory {

    protected static ContainerFactory INSTANCE;

    public static IContainer createContainer(IContainerDefinition def){
        return INSTANCE.newContainer(def);
    }

    protected abstract IContainer newContainer(IContainerDefinition def);
}
