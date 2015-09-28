package net.darkaqua.blacksmith.api.tileentity;

import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.storage.IDataTagCompound;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.util.ClientSideOnly;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.minecraft.network.Packet;

public interface ITileEntity {

	IWorld getWorld();
	
	void setWorld(IWorld world);
	
	BlockLoc getBlockPos();
	
	void setBlockPos(BlockLoc pos);
	
	boolean isValid();
	
	void setValid(boolean valid);
	
	void setModified();
	
	void loadData(IDataTagCompound tag);
	
	void saveData(IDataTagCompound tag);
	
	Packet getDescriptionPacket();
	
	void onDescriptionPackArrives(Packet packet);
	
	void onChunkUnload();
	
	boolean shouldRecreate(IWorld world, BlockLoc pos, IIBlockState oldState, IIBlockState newSate);
	
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
