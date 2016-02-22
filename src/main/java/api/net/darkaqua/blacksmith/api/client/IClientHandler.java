package net.darkaqua.blacksmith.api.client;

import net.darkaqua.blacksmith.api.client.registry.IParticleManager;
import net.darkaqua.blacksmith.api.client.registry.IRenderManager;
import net.darkaqua.blacksmith.api.client.registry.IRenderRegistry;
import net.darkaqua.blacksmith.api.client.registry.ISoundHandler;

/**
 * Created by cout970 on 22/02/2016.
 */
public interface IClientHandler {

    IRenderRegistry getRenderRegistry();

    IRenderManager getRenderManager();

    IParticleManager getParticleManager();

    ISoundHandler getSoundHandler();
}
