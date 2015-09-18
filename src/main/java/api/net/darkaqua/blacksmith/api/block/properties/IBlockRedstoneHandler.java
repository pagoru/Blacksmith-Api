package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public interface IBlockRedstoneHandler {
	
	IBlock getBlock();

	boolean canConnectRedstone(IBlockAccess world, BlockPos pos, EnumFacing side);
	
	int getComparatorInputOverride(World worldIn, BlockPos pos);
	
	boolean hasComparatorInputOverride();
	
	boolean canProvidePower();
	
	int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side);
	
	int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side);
}
