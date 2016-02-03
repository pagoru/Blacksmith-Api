package net.darkaqua.blacksmith.api.modloader;

import net.darkaqua.blacksmith.api.intermod.IModPipe;

/**
 * Created by cout970 on 03/02/2016.
 */
public interface IModIdentifier {

    String getModID();

    String getModName();

    String getModVersion();

    Object getModInstance();

    IModPipe getModPipe();
}
