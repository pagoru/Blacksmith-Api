package net.darkaqua.blacksmith.mod.world;

import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Vect3i;
import net.darkaqua.blacksmith.api.world.IIChunkProvider;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 08/11/2015.
 */
public class WorldWrapper implements IWorld {

    private World world;

    public WorldWrapper(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public IBlockData getBlockData(Vect3i pos) {
        return MCInterface.fromIBlockState(world.getBlockState(MCInterface.toBlockPos(pos)));
    }

    @Override
    public boolean setBlockData(IBlockData variant, Vect3i posiction, int flags) {
        return world.setBlockState(MCInterface.toBlockPos(posiction), MCInterface.toIBlockState(variant), flags);
    }

    @Override
    public ITileEntity getTileEntity(Vect3i position) {
        return MCInterface.fromTileEntity(world.getTileEntity(MCInterface.toBlockPos(position)));
    }

    @Override
    public void removeTileEntity(Vect3i position) {
        world.removeTileEntity(MCInterface.toBlockPos(position));
    }

    @Override
    public boolean isBlockLoaded(Vect3i position) {
        return world.isBlockLoaded(MCInterface.toBlockPos(position));
    }

    @Override
    public boolean isAreaLoaded(Cube area) {
        return world.isAreaLoaded(new StructureBoundingBox((int) area.minX(), (int) area.minY(), (int) area.minZ(), (int) area.maxX(), (int) area.maxY(), (int) area.maxZ()));
    }

    @Override
    public int getWorldDimension() {
        return world.provider.getDimensionId();
    }

    @Override
    public IIChunkProvider getChunkProvider() {
        return MCInterface.fromIChunkProvider(world.getChunkProvider());
    }

    @Override
    public long getWorldTime() {
        return world.getTotalWorldTime();
    }

    @Override
    public boolean isThundering() {
        return world.isThundering();
    }

    @Override
    public boolean isRaining() {
        return world.isRaining();
    }

    @Override
    public List<IPlayer> getPlayers() {
        return world.getPlayers(EntityPlayer.class, input -> true).stream().map(MCInterface::toPlayer).collect(Collectors.toList());
    }

    @Override
    public List<IEntity> getLoadedEntities() {
        return world.getLoadedEntityList().stream().map(MCInterface::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<IEntity> getEntitiesInsideCube(Cube cube) {
        return world.getEntitiesWithinAABBExcludingEntity(null, MCInterface.toAxisAlignedBB(cube)).stream().map(MCInterface::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void spawnEntity(IEntity entity) {
        world.spawnEntityInWorld(MCInterface.toEntity(entity));
    }
}
