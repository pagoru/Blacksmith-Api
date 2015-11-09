package net.darkaqua.blacksmith.api.block;

import net.darkaqua.blacksmith.api.block.properties.*;
import net.darkaqua.blacksmith.api.item.IItem;

/**
 * 
 * @author cout970
 *
 */
public interface IBlock {

	/**
	 * 
	 * @return The internal block name in the form: tile.blockname.name
	 */
	String getUnlocalizedName();
	
	String getLocalizedName();
	
	IBlockLightHandler getLightHandler();
	
	IBlockStateHandler getStateHandler();
	
	IBlockPhysicsHandler getPhysicsHandler();
	
	IBlockHarvestHandler getHarvestHandler();
	
	IBlockRedstoneHandler getRedstoneHandler();
	
	IBlockRenderHandler getRenderHandler();
	
	IBlockTileEntityHandler getTileEntityHandler();
	
	IBlockTickHandler getTickHandler();
	
	IBlockEventHandler getEventHandler();
	
	IItem getItemBlock();
}
