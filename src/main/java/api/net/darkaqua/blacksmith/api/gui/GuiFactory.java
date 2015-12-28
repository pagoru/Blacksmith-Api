package net.darkaqua.blacksmith.api.gui;

import net.darkaqua.blacksmith.api.container.IContainer;

/**
 * Created by cout970 on 25/12/2015.
 */
public abstract class GuiFactory {

    protected static GuiFactory INSTANCE;

    public static IGui createGui(IGuiDefinition def, IContainer cont){
        return INSTANCE.newGui(def, cont);
    }

    protected abstract IGui newGui(IGuiDefinition def, IContainer cont);
}
