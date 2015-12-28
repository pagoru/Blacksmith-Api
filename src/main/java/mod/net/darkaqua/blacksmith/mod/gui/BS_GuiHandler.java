package net.darkaqua.blacksmith.mod.gui;

import net.darkaqua.blacksmith.api.container.IContainer;
import net.darkaqua.blacksmith.api.gui.IGui;
import net.darkaqua.blacksmith.api.gui.IGuiCreationHandler;
import net.darkaqua.blacksmith.api.util.Vect3i;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.container.BS_Container;
import net.darkaqua.blacksmith.mod.container.ContainerComponent;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by cout970 on 28/12/2015.
 */
public class BS_GuiHandler implements IGuiHandler {

    private BlacksmithModContainer modOwner;
    private IGuiCreationHandler handler;

    public BS_GuiHandler(BlacksmithModContainer mod, IGuiCreationHandler handler) {
        modOwner = mod;
        this.handler = handler;
    }

    public BS_Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        IContainer handler = this.handler.getServerContainer(MCInterface.toPlayer(player), new WorldRef(MCInterface.fromWorld(world), new Vect3i(x,y,z)), ID);
        return new BS_Container((ContainerComponent) handler);
    }

    public BS_Gui getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        IGui handler = this.handler.getClientGui(MCInterface.toPlayer(player), new WorldRef(MCInterface.fromWorld(world), new Vect3i(x,y,z)), id);
        return (BS_Gui) handler;
    }
}
