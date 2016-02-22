package net.darkaqua.blacksmith.api.common.world;

import net.darkaqua.blacksmith.api.common.util.vectors.Vect2i;

/**
 * Created by cout970 on 18/12/2015.
 */
public interface IIChunkProvider {

    /**
     * Returns if a chunk exists in this position
     */
    boolean chunkExists(Vect2i pos);

    /**
     * Gets the chunk in the specific location, if the chunk doesn't exist and is in the server side, this will generate a new one
     */
    IChunk provideChunk(Vect2i pos);

    /**
     * Converts the instance data to a readable string.
     */
    String makeString();

    /**
     * Returns the number of chunks loaded
     */
    int loadedChunkCount();

    /**
     * Regenerate the structures of a chunk
     */
    void recreateStructures(IChunk chunk, Vect2i pos);

    /**
     * Fills the chunk with stuff
     */
    void populateChunk(IIChunkProvider provider, Vect2i pos);

    /**
     * Returns if this chunkProvider can save data
     */
    boolean canSave();

    Object getInternalChunkProvider();
}
