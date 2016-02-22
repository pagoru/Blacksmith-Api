package net.darkaqua.blacksmith.mod.common.world;

import net.darkaqua.blacksmith.api.common.util.vectors.Vect2i;
import net.darkaqua.blacksmith.api.common.world.IChunk;
import net.darkaqua.blacksmith.api.common.world.IIChunkProvider;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.world.chunk.IChunkProvider;

/**
 * Created by cout970 on 18/12/2015.
 */
public class IChunkProviderWrapper implements IIChunkProvider {

    private IChunkProvider provider;

    public IChunkProviderWrapper(IChunkProvider provider) {
        this.provider = provider;
    }

    public IChunkProvider getChunkProvider() {
        return provider;
    }

    @Override
    public boolean chunkExists(Vect2i pos) {
        return provider.chunkExists(pos.getX(), pos.getY());
    }

    @Override
    public IChunk provideChunk(Vect2i pos) {
        return MCInterface.fromChunk(provider.provideChunk(pos.getX(), pos.getY()));
    }

    @Override
    public String makeString() {
        return provider.makeString();
    }

    @Override
    public int loadedChunkCount() {
        return provider.getLoadedChunkCount();
    }

    @Override
    public void recreateStructures(IChunk chunk, Vect2i pos) {
        provider.recreateStructures(MCInterface.toChunk(chunk), pos.getX(), pos.getY());
    }

    @Override
    public void populateChunk(IIChunkProvider prov, Vect2i pos) {
        provider.populate(MCInterface.toIChunkProvider(prov), pos.getX(), pos.getY());
    }

    @Override
    public boolean canSave() {
        return provider.canSave();
    }

    @Override
    public Object getInternalChunkProvider() {
        return provider;
    }
}
