package net.darkaqua.blacksmith.api.tileentity;

import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.network.packet.IDescriptionPacket;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 13/11/2015.
 */
public interface ITileEntityDefinition {

    void onLoad(ITileEntity parent);

    void update();

    void loadData(IDataCompound tag);

    void saveData(IDataCompound tag);

    IDescriptionPacket getDescriptionPacket();

    void onDescriptionPacketArrives(IDescriptionPacket packet);

    void onChunkUnload();

    boolean shouldRecreate(WorldRef ref, IIBlockState oldState, IIBlockState newSate);

    void onBlockChange();

    void onClientDataArrive(int id, int data);

    double getRenderDistance();

    Cube getRenderBox();
}
