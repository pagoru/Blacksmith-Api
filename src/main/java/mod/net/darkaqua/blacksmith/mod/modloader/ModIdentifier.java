package net.darkaqua.blacksmith.mod.modloader;

import net.darkaqua.blacksmith.api.intermod.IModPipe;
import net.darkaqua.blacksmith.api.modloader.IModIdentifier;

/**
 * Created by cout970 on 03/02/2016.
 */
public class ModIdentifier implements IModIdentifier {

    private BlacksmithModContainer container;

    public ModIdentifier(BlacksmithModContainer container) {
        this.container = container;
    }

    public BlacksmithModContainer getContainer() {
        return container;
    }

    @Override
    public String getModID() {
        return container.getModId();
    }

    @Override
    public String getModName() {
        return container.getName();
    }

    @Override
    public String getModVersion() {
        return container.getVersion();
    }

    @Override
    public Object getModInstance() {
        return container.getMod();
    }

    @Override
    public IModPipe getModPipe() {
        return null;
    }
}
