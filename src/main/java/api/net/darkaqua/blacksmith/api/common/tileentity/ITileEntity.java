package net.darkaqua.blacksmith.api.common.tileentity;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.storage.IDataCompound;
import net.darkaqua.blacksmith.api.common.util.raytrace.Cube;
import net.darkaqua.blacksmith.api.common.util.WorldRef;
import net.darkaqua.blacksmith.api.common.util.annotations.ClientSideOnly;
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
	
	boolean shouldRecreate(WorldRef ref, IBlockData oldState, IBlockData newSate);
	
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
