package net.darkaqua.blacksmith.api.block.properties;

import java.util.List;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public interface IBlockHarvestHandler {
	
	IBlock getBlock();

	//TODO change String to ToolTypeEnum
	boolean isToolEffective(String type, IBlockState state);
	
	//TODO change int to an enum
	int getHarvestLevel(IBlockState state);
	
	//TODO change String to ToolTypeEnum
	String getHarvestTool(IBlockState state);
	
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
	
	void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player);
	
	void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, IItemStack stack);
	
	List<IItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune);
}
