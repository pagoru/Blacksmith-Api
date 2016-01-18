package net.darkaqua.blacksmith.api.world;

import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.registry.StaticAccess;
import net.darkaqua.blacksmith.api.render.particle.IParticle;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.Vect3i;

import java.util.List;

public interface IWorld extends IWorldAccess {

	IBlockData getBlockVariant(Vect3i position);

	//flags argument will be changed
	boolean setBlockVariant(IBlockData variant, Vect3i posiction, int flags);

	boolean setBlockVariant(IBlockData variant, Vect3i posiction);

    ITileEntity getTileEntity(Vect3i position);

    void removeTileEntity(Vect3i position);

    boolean isBlockLoaded(Vect3i position);

    boolean isAreaLoaded(Cube area);

    int getWorldDimension();

    IIChunkProvider getChunkProvider();

    long getWorldTime();

    boolean isThundering();

    boolean isRaining();

    List<IPlayer> getPlayers();

    default void addParticle(IParticle particle, Vect3d pos, Vect3d motion){
        StaticAccess.GAME.getParticleManager().addParticle(this, particle, pos, motion);
    }
}

