package net.darkaqua.blacksmith.api.block.properties;

import java.util.List;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockState;
import net.darkaqua.blacksmith.api.entity.Entity;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.ToolType;
import net.darkaqua.blacksmith.api.util.BlockPos;
import net.darkaqua.blacksmith.api.world.IBlockAccess;
import net.darkaqua.blacksmith.api.world.World;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Explosion;

public interface IBlockHarvestHandler {
	
	IBlock getBlock();

	boolean isToolEffective(ToolType type, IBlockState state);
	
	//TODO change int to an enum
	int getHarvestLevel(IBlockState state);
	
	ToolType getHarvestTool(IBlockState state);
	
	int getExpDrop(IBlockAccess world, BlockPos pos, int fortune);
	
	boolean canEntityDestroy(IBlockAccess world, BlockPos pos, Entity entity);
	
	boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player);
	
	boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest);
	
	boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player);
	
	boolean canDropFromExplosion(Explosion explosionIn);
	
	boolean isReplaceable(World world, BlockPos pos);
	
	float getBlockHardness(World world, BlockPos pos);
	
	float getBlockHardness(EntityPlayer player, World world, BlockPos pos);
	
	float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion);
	
	void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player);
	
	void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, IItemStack stack);
	
	List<IItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune);
}
