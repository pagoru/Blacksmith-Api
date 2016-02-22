package net.darkaqua.blacksmith.mod.common.gui;

import net.darkaqua.blacksmith.api.common.gui.IGuiCreationHandler;
import net.darkaqua.blacksmith.api.common.gui.IGuiDefinition;
import net.darkaqua.blacksmith.api.common.util.GameSide;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3i;
import net.darkaqua.blacksmith.api.common.util.WorldRef;
import net.darkaqua.blacksmith.mod.common.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
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
        IGuiDefinition handler = this.handler.getGuiDefinition(MCInterface.toPlayer(player), new WorldRef(MCInterface.fromWorld(world), new Vect3i(x, y, z)), ID, GameSide.SERVER);
        return new BS_Container(handler);
    }

    public BS_Gui getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        IGuiDefinition handler = this.handler.getGuiDefinition(MCInterface.toPlayer(player), new WorldRef(MCInterface.fromWorld(world), new Vect3i(x, y, z)), ID, GameSide.CLIENT);
        return new BS_Gui(handler);
    }
}
