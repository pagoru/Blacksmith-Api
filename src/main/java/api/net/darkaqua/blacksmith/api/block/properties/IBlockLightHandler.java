package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.util.Vector3i;
import net.darkaqua.blacksmith.api.world.IIBlockAccess;

public interface IBlockLightHandler {

	IBlock getBlock();
	
	float getLightOpacity();
	
	float getLightOpacity(IIBlockAccess world, Vector3i pos);
	
	float getLightEmitted();
	
	boolean isOpaque();
}
