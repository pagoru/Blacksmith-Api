package net.darkaqua.blacksmith.api.block.properties;

import java.util.List;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.ToolType;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.util.ClientSideOnly;
import net.darkaqua.blacksmith.api.world.IBlockAccess;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;

public interface IBlockHarvestHandler {
	
	IBlock getBlock();

	boolean isToolEffective(ToolType type, IIBlockState state);
	
	//TODO change int to an enum
	int getHarvestLevel(IIBlockState state);
	
	ToolType getHarvestTool(IIBlockState state);
	
	int getExpDrop(IBlockAccess world, BlockLoc pos, int fortune);
	
	boolean canEntityDestroy(IBlockAccess world, BlockLoc pos, IEntity entity);
		
	boolean canSilkHarvest(IWorld world, BlockLoc pos, IIBlockState state, EntityPlayer player);
	
	boolean canHarvestBlock(IBlockAccess world, BlockLoc pos, EntityPlayer player);
	
	boolean canDropFromExplosion(Explosion explosionIn);
	
	boolean isReplaceable(IWorld world, BlockLoc pos);
	
	float getBlockHardness(IWorld world, BlockLoc pos);
	
	float getBlockHardness(EntityPlayer player, IWorld world, BlockLoc pos);
	
	float getExplosionResistance(IWorld world, BlockLoc pos, IEntity exploder, Explosion explosion);
	
	List<IItemStack> getDrops(IBlockAccess world, BlockLoc pos, IIBlockState state, int fortune);
	
	@ClientSideOnly
	IItemStack getPickBlock(MovingObjectPosition target, IWorld world, BlockLoc pos);
}
