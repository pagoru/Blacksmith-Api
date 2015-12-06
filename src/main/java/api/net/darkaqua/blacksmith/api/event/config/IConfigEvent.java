package net.darkaqua.blacksmith.api.event.config;

import net.darkaqua.blacksmith.api.event.IForgeEvent;

/**
 * Created by cout970 on 06/12/2015.
 */
public interface IConfigEvent extends IForgeEvent {

    String getModID();

    boolean isWorldRunning();

    boolean requiresMcRestart();

    String getConfigID();
}
