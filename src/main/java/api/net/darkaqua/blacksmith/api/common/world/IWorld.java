package net.darkaqua.blacksmith.api.common.world;

import net.darkaqua.blacksmith.api.Game;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.entity.IEntity;
import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.client.particle.IParticle;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.common.util.raytrace.Cube;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3i;

import java.util.List;

public interface IWorld extends IWorldAccess {

    IBlockData getBlockData(Vect3i position);

    boolean setBlockData(IBlockData variant, Vect3i position, int flags);

    default boolean setBlockData(IBlockData variant, Vect3i position) {
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

    default void addParticle(IParticle particle, Vect3d pos, Vect3d motion) {
        Game.getClientHandler().getParticleManager().addParticle(this, particle, pos, motion);
    }

    void spawnEntity(IEntity entity);
}

