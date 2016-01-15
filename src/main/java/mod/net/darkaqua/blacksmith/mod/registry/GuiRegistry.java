package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.gui.IGuiCreationHandler;
import net.darkaqua.blacksmith.api.registry.IGuiRegistry;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.gui.BS_GuiHandler;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.util.MCInterface;
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
    public void openGui(IPlayer player, WorldRef ref, int id, Object modInstance) {
        MCInterface.fromPlayer(player).openGui(modInstance, id, MCInterface.toWorld(ref.getWorld()), ref.getPosition().getX(), ref.getPosition().getY(), ref.getPosition().getZ());
    }
}
