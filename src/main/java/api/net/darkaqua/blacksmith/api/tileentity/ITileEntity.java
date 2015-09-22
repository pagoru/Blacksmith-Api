package net.darkaqua.blacksmith.api.tileentity;

import net.darkaqua.blacksmith.api.block.IBlockState;
import net.darkaqua.blacksmith.api.storage.IDataTagCompound;
import net.darkaqua.blacksmith.api.util.BlockPos;
import net.darkaqua.blacksmith.api.util.ClientSideOnly;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.world.World;
import net.minecraft.network.Packet;

public interface ITileEntity {

	World getWorld();
	
	void setWorld(World world);
	
	BlockPos getBlockPos();
	
	void setBlockPos(BlockPos pos);
	
	boolean isValid();
	
	void setValid(boolean valid);
	
	void setModified();
	
	void loadData(IDataTagCompound tag);
	
	void saveData(IDataTagCompound tag);
	
	Packet getDescriptionPacket();
	
	void onDescriptionPackArrives(Packet packet);
	
	void onChunkUnload();
	
	boolean shouldRecreate(World world, BlockPos pos, IBlockState oldState, IBlockState newSate);
	
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
