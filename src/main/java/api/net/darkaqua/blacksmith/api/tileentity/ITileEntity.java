package net.darkaqua.blacksmith.api.tileentity;

import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.ClientSideOnly;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.minecraft.network.Packet;

public interface ITileEntity {

    /**
     * Returns the world and the position of the tileEntity
     */
    WorldRef getWorldRef();

    void setWorldRef(WorldRef ref);

    boolean isValid();

    void setValid(boolean valid);

    void setModified();

    void loadData(IDataCompound tag);

    void saveData(IDataCompound tag);

    Packet getDescriptionPacket();

    void onChunkUnload();

    boolean shouldRecreate(WorldRef ref, IBlockVariant oldState, IBlockVariant newSate);

    void onBlockChange();

    void onClientDataArrive(int id, int data);

    ITileEntityDefinition getTileEntityDefinition();

    @ClientSideOnly()
    double getRenderDistance();

    @ClientSideOnly()
    boolean canRenderInPass(int pass);

    @ClientSideOnly
    Cube getRenderBox();

    @ClientSideOnly
    boolean canRenderBreaking();

    Object getInternalTileEntity();
}
