package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.world.IBlockAccess;

public interface IBlockLightHandler {

	IBlock getBlock();
	
	float getLightOpacity();
	
	float getLightOpacity(IBlockAccess world, BlockLoc pos);
	
	float getLightEmited();
	
	boolean isOpaque();
}
