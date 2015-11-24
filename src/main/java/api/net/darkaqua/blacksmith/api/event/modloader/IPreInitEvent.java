package net.darkaqua.blacksmith.api.event.modloader;

import net.darkaqua.blacksmith.api.event.IForgeEvent;

import java.io.File;

/**
 * Created by cout970 on 08/11/2015.
 */
public interface IPreInitEvent extends IForgeEvent{

    File getModConfigurationDirectory();
    File getSuggestedConfigurationFile();
    File getSourceFile();
}
