package net.darkaqua.blacksmith.api.block.properties.base;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.block.properties.IBlockEventHandler;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vector3d;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Explosion;


public class SimpleBlockEventHandler implements IBlockEventHandler{

	protected IBlock block;

	public SimpleBlockEventHandler(IBlock block){
		this.block = block;
	}
	
	@Override
	public IBlock getBlock() {
		return block;
	}

	@Override
	public void onBlockHarvested(IWorld world, BlockLoc pos, IIBlockState state, EntityPlayer player) {
		
	}

	@Override
	public void onBlockPlacedBy(IWorld world, BlockLoc pos, IIBlockState state, EntityLivingBase placer, IItemStack stack) {
		
	}

	@Override
	public boolean removedByPlayer(IWorld world, BlockLoc pos, EntityPlayer player, boolean willHarvest) {
		return false;
	}

	@Override
	public void onBlockAdded(IWorld fromWorld, BlockLoc fromBlockPos, IIBlockState fromBlockState) {
	}

	@Override
	public void onBlockBreaks(IWorld world, BlockLoc pos, IIBlockState state) {
		
	}

	@Override
	public void onBlockDestroyedByExplosion(IWorld world, BlockLoc pos, Explosion explosion) {
		
	}

	@Override
	public void onNeighborBlockChange(IWorld world, BlockLoc pos, IIBlockState state, IBlock neighbor) {
		
	}

	@Override
	public boolean onBlockActivated(IWorld world, BlockLoc pos, IIBlockState state, EntityPlayer player, Direction side, Vector3d vector3d) {
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(IWorld world, BlockLoc pos, IEntity entity) {
		
	}

	@Override
	public IIBlockState onBlockPlaced(IWorld world, BlockLoc pos, Direction side, IEntity entity, Vector3d hit, int metadata) {
		return block.getStateHandler().getStateFromMeta(metadata);
	}

	@Override
	public void onBlockClicked(IWorld world, BlockLoc pos, EntityPlayer player) {
		
	}
}
