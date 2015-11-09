package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vector3d;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Explosion;

public interface IBlockEventHandler {

	IBlock getBlock();
	
	void onBlockHarvested(WorldRef ref, IIBlockState state, EntityPlayer player);
	
	void onBlockPlacedBy(WorldRef ref, IIBlockState state, EntityLivingBase placer, IItemStack stack);
	
	boolean removedByPlayer(WorldRef ref, EntityPlayer player, boolean willHarvest);

	void onBlockAdded(WorldRef ref, IIBlockState fromBlockState);

	void onBlockBreaks(WorldRef ref, IIBlockState state);
	
	void onBlockDestroyedByExplosion(WorldRef ref, Explosion explosion);
	
	void onNeighborBlockChange(WorldRef ref, IIBlockState state, IBlock neighbor);
	
	boolean onBlockActivated(WorldRef ref, IIBlockState state, EntityPlayer player, Direction side, Vector3d vector3d);
	
	void onEntityCollidedWithBlock(WorldRef ref, IEntity entity);

	IIBlockState onBlockPlaced(WorldRef ref, Direction side, IEntity entity, Vector3d hit, int metadata);

	void onBlockClicked(WorldRef ref, EntityPlayer player);
}
