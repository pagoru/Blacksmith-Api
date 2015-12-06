package net.darkaqua.blacksmith.mod.event.config;

import net.darkaqua.blacksmith.api.event.config.IPostConfigChangedEvent;
import net.darkaqua.blacksmith.mod.event.BaseEvent;
import net.darkaqua.blacksmith.mod.exceptions.BlacksmithInternalException;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;

/**
 * Created by cout970 on 06/12/2015.
 */
public class BS_PostConfigChangedEvent extends BaseEvent implements IPostConfigChangedEvent {

    private ConfigChangedEvent.PostConfigChangedEvent event;

    public BS_PostConfigChangedEvent(ConfigChangedEvent.PostConfigChangedEvent event){
        if (event == null)
            throw new BlacksmithInternalException("Error trying to create a PostConfigChangedEvent");
        this.event = event;
    }

    @Override
    public String getModID() {
        return event.modID;
    }

    @Override
    public boolean isWorldRunning() {
        return event.isWorldRunning;
    }

    @Override
    public boolean requiresMcRestart() {
        return event.isWorldRunning;
    }

    @Override
    public String getConfigID() {
        return event.configID;
    }

    @Override
    public Object getForgeEvent() {
        return event;
    }
}
