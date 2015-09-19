package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockState;
import net.darkaqua.blacksmith.api.util.BlockPos;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.world.IBlockAccess;
import net.darkaqua.blacksmith.api.world.World;
import net.minecraft.util.MovingObjectPosition;

public interface IBlockRenderHandler {

	IBlock getBlock();
	
	boolean addDestroyEffects(World world, BlockPos pos, net.minecraft.client.particle.EffectRenderer effectRenderer);
	
	boolean addHitEffects(World worldObj, MovingObjectPosition target, net.minecraft.client.particle.EffectRenderer effectRenderer);
	
	int getBlockColor();
	
	int getRenderColor(IBlockState state);
	
	int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass);
	
	//TODO change int to an enum
	int getBlockRenderLayer();
	
	boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, Direction side);
}
