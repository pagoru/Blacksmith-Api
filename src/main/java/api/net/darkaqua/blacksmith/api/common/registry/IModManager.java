package net.darkaqua.blacksmith.api.common.registry;

import net.darkaqua.blacksmith.api.common.modloader.IModIdentifier;

/**
 * Created by cout970 on 03/02/2016.
 */
public interface IModManager {

    IModIdentifier getIdentifier(Object instance);
}
