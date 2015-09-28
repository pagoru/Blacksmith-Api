package net.darkaqua.blacksmith.api.tileentity;

import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.storage.IDataTagCompound;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.minecraft.network.Packet;

public class SimpleTileEntity implements ITileEntity{

	private IWorld world;
	private BlockLoc pos;
	private boolean valid;
	protected IIBlockState blockCache;
	
	@Override
	public IWorld getWorld() {
		return world;
	}

	@Override
	public void setWorld(IWorld world) {
		this.world = world;
	}

	@Override
	public BlockLoc getBlockPos() {
		return pos.copy();
	}

	@Override
	public void setBlockPos(BlockLoc pos) {
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
		pos = new BlockLoc(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"));
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
	public boolean shouldRecreate(IWorld world, BlockLoc pos, IIBlockState oldState, IIBlockState newSate) {
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
	
	public IIBlockState getBlockState(){
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
