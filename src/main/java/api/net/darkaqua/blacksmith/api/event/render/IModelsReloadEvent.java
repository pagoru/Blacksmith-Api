package net.darkaqua.blacksmith.api.event.render;

import net.darkaqua.blacksmith.api.event.IEvent;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;

/**
 * Created by cout970 on 04/02/2016.
 */
public interface IModelsReloadEvent extends IEvent {

    IModelRegistry getModelRegistry();
}
