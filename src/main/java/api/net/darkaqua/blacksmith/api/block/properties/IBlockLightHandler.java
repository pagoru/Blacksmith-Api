package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.util.BlockPos;
import net.darkaqua.blacksmith.api.world.IBlockAccess;

public interface IBlockLightHandler {

	IBlock getBlock();
	
	float getLightOpacity();
	
	float getLightOpacity(IBlockAccess world, BlockPos pos);
	
	float getLightEmited();
	
	boolean isTransparent();
	
	boolean isOpaque();
	
	float getAmbientOcclusionLightValue();
}
