package net.darkaqua.blacksmith.api.tileentity;

import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.network.packet.ITileEntityUpdatePacket;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 13/11/2015.
 */
public interface ITileEntityDefinition {

    ITileEntity getParent();

    void bindParent(ITileEntity parent);

    void onLoad();

    void onDelete();

    void update();

    void loadData(IDataCompound tag);

    void saveData(IDataCompound tag);

    ITileEntityUpdatePacket getUpdatePacket();

    void onUpdatePacketArrives(ITileEntityUpdatePacket packet);

    void onChunkUnload();

    boolean shouldRecreate(WorldRef ref, IBlockVariant oldState, IBlockVariant newSate);

    void onBlockChange();

    void onClientDataArrive(int id, int data);

    double getRenderDistance();

    Cube getRenderBox();
}
