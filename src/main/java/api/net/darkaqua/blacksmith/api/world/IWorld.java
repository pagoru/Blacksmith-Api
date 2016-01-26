package net.darkaqua.blacksmith.api.world;

import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.registry.StaticAccess;
import net.darkaqua.blacksmith.api.render.particle.IParticle;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.Vect3i;

import java.util.List;

public interface IWorld extends IWorldAccess {

	IBlockData getBlockData(Vect3i position);

	boolean setBlockData(IBlockData variant, Vect3i position, int flags);

	default boolean setBlockData(IBlockData variant, Vect3i position){
        return setBlockData(variant, position, 3);
    }

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

    List<IEntity> getLoadedEntities();

    List<IEntity> getEntitiesInsideCube(Cube cube);

    default void addParticle(IParticle particle, Vect3d pos, Vect3d motion){
        StaticAccess.GAME.getParticleManager().addParticle(this, particle, pos, motion);
    }

    void spawnEntity(IEntity entity);
}

