package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.modloader.IModIdentifier;

/**
 * Created by cout970 on 03/02/2016.
 */
public interface IModManager {

    IModIdentifier getIdentifier(Object instance);
}
