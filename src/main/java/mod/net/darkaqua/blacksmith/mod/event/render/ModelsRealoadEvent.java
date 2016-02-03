package net.darkaqua.blacksmith.mod.event.render;

import net.darkaqua.blacksmith.api.event.render.IModelsReloadEvent;
import net.darkaqua.blacksmith.api.registry.IModelRegistry;
import net.darkaqua.blacksmith.mod.event.BaseEvent;

/**
 * Created by cout970 on 04/02/2016.
 */
public class ModelsRealoadEvent extends BaseEvent implements IModelsReloadEvent {

    private IModelRegistry registry;

    public ModelsRealoadEvent(IModelRegistry registry) {
        this.registry = registry;
    }

    @Override
    public IModelRegistry getModelRegistry() {
        return registry;
    }
}
