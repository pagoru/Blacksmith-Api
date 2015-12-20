package net.darkaqua.blacksmith.mod.world;

import net.darkaqua.blacksmith.api.world.IChunk;
import net.minecraft.world.chunk.Chunk;

/**
 * Created by cout970 on 18/12/2015.
 */
public class ChunkWrapper implements IChunk {

    private Chunk chunk;

    public ChunkWrapper(Chunk chunk){
        this.chunk = chunk;
    }

    public Chunk getChunk(){
        return chunk;
    }

    @Override
    public Object getInternalChunk() {
        return chunk;
    }
}
