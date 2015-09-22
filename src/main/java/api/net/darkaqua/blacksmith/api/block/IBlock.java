package net.darkaqua.blacksmith.api.block;

import net.darkaqua.blacksmith.api.block.properties.IBlockClientHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockHarvestHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockLightHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockPhysicsHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockRedstoneHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockStateHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockTickHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockTileEntityHandler;
import net.darkaqua.blacksmith.api.inventory.IItemStack;

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
	
	IBlockClientHandler getClientHandler();
	
	IBlockTileEntityHandler getTileEntityHandler();
	
	IBlockTickHandler getTickHandler();
	
	IItemStack toItemStack(IBlockState state);
	
}
