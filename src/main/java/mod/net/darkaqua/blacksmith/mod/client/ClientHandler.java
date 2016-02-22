package net.darkaqua.blacksmith.mod.client;

import net.darkaqua.blacksmith.api.client.IClientHandler;
import net.darkaqua.blacksmith.api.client.registry.IParticleManager;
import net.darkaqua.blacksmith.api.client.registry.IRenderManager;
import net.darkaqua.blacksmith.api.client.registry.IRenderRegistry;
import net.darkaqua.blacksmith.api.client.registry.ISoundHandler;
import net.darkaqua.blacksmith.mod.common.registry.ParticleManager;
import net.darkaqua.blacksmith.mod.common.registry.RenderManager;
import net.darkaqua.blacksmith.mod.common.registry.RenderRegistry;
import net.darkaqua.blacksmith.mod.common.registry.SoundHandler;

/**
 * Created by cout970 on 22/02/2016.
 */
public class ClientHandler implements IClientHandler {

    public static final ClientHandler INSTANCE = new ClientHandler();

    private ClientHandler(){}

    @Override
    public IRenderRegistry getRenderRegistry() {
        return RenderRegistry.INSTANCE;
    }

    @Override
    public IRenderManager getRenderManager() {
        return RenderManager.INSTANCE;
    }

    @Override
    public IParticleManager getParticleManager() {
        return ParticleManager.INSTANCE;
    }

    @Override
    public ISoundHandler getSoundHandler() {
        return SoundHandler.INSTANCE;
    }
}
