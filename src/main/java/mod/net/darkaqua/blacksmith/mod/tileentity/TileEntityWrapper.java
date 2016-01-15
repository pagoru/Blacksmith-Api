package net.darkaqua.blacksmith.mod.tileentity;

import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by cout970 on 14/11/2015.
 */
public class TileEntityWrapper implements ITileEntity {

    private TileEntity tile;

    public TileEntityWrapper(TileEntity t) {
        this.tile = t;
    }

    public TileEntity getTileEntity() {
        return tile;
    }

    @Override
    public WorldRef getWorldRef() {
        return new WorldRef(MCInterface.fromWorld(tile.getWorld()), MCInterface.fromBlockPos(tile.getPos()));
    }

    @Override
    public void setWorldRef(WorldRef ref) {
        tile.setWorldObj(MCInterface.toWorld(ref.getWorld()));
        tile.setPos(MCInterface.toBlockPos(ref.getPosition()));
    }

    @Override
    public boolean isValid() {
        return !tile.isInvalid();
    }

    @Override
    public void setValid(boolean valid) {
        if (valid) {
            tile.validate();
        } else {
            tile.invalidate();
        }
    }

    @Override
    public void setModified() {
        tile.markDirty();
    }

    @Override
    public void loadData(IDataCompound tag) {
        tile.readFromNBT(MCInterface.toNBTCompound(tag));
    }

    @Override
    public void saveData(IDataCompound tag) {
        tile.writeToNBT(MCInterface.toNBTCompound(tag));
    }

    @Override
    public Packet getDescriptionPacket() {
        return tile.getDescriptionPacket();
    }

    @Override
    public void onChunkUnload() {
        tile.onChunkUnload();
    }

    @Override
    public boolean shouldRecreate(WorldRef ref, IBlockVariant oldState, IBlockVariant newSate) {
        return tile.shouldRefresh(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()), MCInterface.toIBlockState(oldState), MCInterface.toIBlockState(newSate));
    }

    @Override
    public void onBlockChange() {
        tile.updateContainingBlockInfo();
    }

    @Override
    public void onClientDataArrive(int id, int data) {
        tile.receiveClientEvent(id, data);
    }

    @Override
    public ITileEntityDefinition getTileEntityDefinition() {
        if (tile instanceof BS_TileEntity) {
            return ((BS_TileEntity) tile).getTileEntityDefinition();
        }
        return null;
    }

    @Override
    public double getRenderDistance() {
        return Math.sqrt(tile.getMaxRenderDistanceSquared());
    }

    @Override
    public boolean canRenderInPass(int pass) {
        return tile.shouldRenderInPass(pass);
    }

    @Override
    public Cube getRenderBox() {
        return MCInterface.fromAxisAlignedBB(tile.getRenderBoundingBox());
    }

    @Override
    public boolean canRenderBreaking() {
        return tile.canRenderBreaking();
    }

    @Override
    public Object getInternalTileEntity() {
        return tile;
    }
}
