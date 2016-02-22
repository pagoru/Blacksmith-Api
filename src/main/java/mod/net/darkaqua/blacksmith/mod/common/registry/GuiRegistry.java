package net.darkaqua.blacksmith.mod.common.registry;

import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.gui.IGuiCreationHandler;
import net.darkaqua.blacksmith.api.common.modloader.IModIdentifier;
import net.darkaqua.blacksmith.api.common.registry.IGuiRegistry;
import net.darkaqua.blacksmith.api.common.util.WorldRef;
import net.darkaqua.blacksmith.mod.common.gui.BS_GuiHandler;
import net.darkaqua.blacksmith.mod.common.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.common.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by cout970 on 28/12/2015.
 */
public class GuiRegistry implements IGuiRegistry {

    public static final GuiRegistry INSTANCE = new GuiRegistry();

    private GuiRegistry() {
    }

    @Override
    public void registerGuiCreationHandler(IGuiCreationHandler handler) {
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        NetworkRegistry.INSTANCE.registerGuiHandler(mod.getMod(), new BS_GuiHandler(mod, handler));
    }

    @Override
    public void openGui(IPlayer player, WorldRef ref, int id, IModIdentifier modInstance) {
        MCInterface.fromPlayer(player).openGui(modInstance.getModInstance(), id, MCInterface.toWorld(ref.getWorld()), ref.getPosition().getX(), ref.getPosition().getY(), ref.getPosition().getZ());
    }
}
