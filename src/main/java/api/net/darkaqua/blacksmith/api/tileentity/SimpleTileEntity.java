package net.darkaqua.blacksmith.api.tileentity;

import net.darkaqua.blacksmith.api.block.IBlockState;
import net.darkaqua.blacksmith.api.storage.IDataTagCompound;
import net.darkaqua.blacksmith.api.util.BlockPos;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.world.World;
import net.minecraft.network.Packet;

public class SimpleTileEntity implements ITileEntity{

	private World world;
	private BlockPos pos;
	private boolean valid;
	protected IBlockState blockCache;
	
	@Override
	public World getWorld() {
		return world;
	}

	@Override
	public void setWorld(World world) {
		this.world = world;
	}

	@Override
	public BlockPos getBlockPos() {
		return pos.copy();
	}

	@Override
	public void setBlockPos(BlockPos pos) {
		pos = pos.copy();
	}

	@Override
	public boolean isValid() {
		return valid;
	}

	@Override
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public void setModified() {
		//TODO 
	}

	@Override
	public void loadData(IDataTagCompound tag) {
		pos = new BlockPos(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"));
	}

	@Override
	public void saveData(IDataTagCompound tag) {
		tag.setInteger("x", pos.getX());
		tag.setInteger("y", pos.getY());
		tag.setInteger("z", pos.getZ());
	}

	@Override
	public Packet getDescriptionPacket() {
		return null;
	}

	@Override
	public void onChunkUnload() {}

	@Override
	public boolean shouldRecreate(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return false;
	}

	@Override
	public double getRenderDistance() {
		return 64;//vanilla value is 32 but it's not enough
	}

	@Override
	public boolean canRenderInPass(int pass) {
		return pass == 0;
	}

	@Override
	public Cube getRenderBox() {
		return Cube.fullBlock();
	}

	@Override
	public boolean canRenderBreaking() {
		return false;
	}
	
	public IBlockState getBlockState(){
		if(blockCache == null){
			blockCache = world.getBlockState(pos);
		}
		return blockCache;
	}

	@Override
	public void onBlockChange() {
		blockCache = null;
	}

	@Override
	public void onDescriptionPackArrives(Packet packet) {}

	@Override
	public void onClientDataArrive(int id, int data) {}

}
