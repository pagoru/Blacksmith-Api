package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vector3d;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Explosion;

public interface IBlockEventHandler {

	IBlock getBlock();
	
	void onBlockHarvested(IWorld world, BlockLoc pos, IIBlockState state, EntityPlayer player);
	
	void onBlockPlacedBy(IWorld world, BlockLoc pos, IIBlockState state, EntityLivingBase placer, IItemStack stack);
	
	boolean removedByPlayer(IWorld world, BlockLoc pos, EntityPlayer player, boolean willHarvest);

	void onBlockAdded(IWorld fromWorld, BlockLoc fromBlockPos, IIBlockState fromBlockState);

	void onBlockBreaks(IWorld world, BlockLoc pos, IIBlockState state);
	
	void onBlockDestroyedByExplosion(IWorld world, BlockLoc pos, Explosion explosion);
	
	void onNeighborBlockChange(IWorld world, BlockLoc pos, IIBlockState state, IBlock neighbor);
	
	boolean onBlockActivated(IWorld world, BlockLoc pos, IIBlockState state, EntityPlayer player, Direction side, Vector3d vector3d);
	
	void onEntityCollidedWithBlock(IWorld world, BlockLoc pos, IEntity entity);

	IIBlockState onBlockPlaced(IWorld world, BlockLoc pos, Direction side, IEntity entity, Vector3d hit, int metadata);

	void onBlockClicked(IWorld world, BlockLoc pos, EntityPlayer player);
}
