package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public interface IBlockRenderHandler {

	IBlock getBlock();
	
	boolean addDestroyEffects(World world, BlockPos pos, net.minecraft.client.particle.EffectRenderer effectRenderer);
	
	boolean addHitEffects(World worldObj, MovingObjectPosition target, net.minecraft.client.particle.EffectRenderer effectRenderer);
	
	int getBlockColor();
	
	int getRenderColor(IBlockState state);
	
	int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass);
	
	//boolean canRenderInLayer(EnumWorldBlockLayer layer)
}
