package net.darkaqua.blacksmith.api.tileentity;

import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.ClientSideOnly;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.minecraft.network.Packet;

public interface ITileEntity {
	
	WorldRef getWorldRef();
	
	void setWorldRef(WorldRef ref);
	
	boolean isValid();
	
	void setValid(boolean valid);
	
	void setModified();
	
	void loadData(IDataCompound tag);
	
	void saveData(IDataCompound tag);
	
	Packet getDescriptionPacket();
	
	void onDescriptionPackArrives(Packet packet);
	
	void onChunkUnload();
	
	boolean shouldRecreate(WorldRef ref, IIBlockState oldState, IIBlockState newSate);
	
	void onBlockChange();
	
	void onClientDataArrive(int id, int data);
	
	@ClientSideOnly()
	double getRenderDistance();
	
	@ClientSideOnly()
	boolean canRenderInPass(int pass);
	
	@ClientSideOnly
	Cube getRenderBox();
	
	@ClientSideOnly
	boolean canRenderBreaking();
}