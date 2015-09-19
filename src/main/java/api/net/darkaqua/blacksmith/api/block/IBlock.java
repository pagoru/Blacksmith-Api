package net.darkaqua.blacksmith.api.block;

import net.darkaqua.blacksmith.api.block.properties.IBlockCreativeTabHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockHarvestHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockLightHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockPhysicsHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockRedstoneHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockRenderHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockStateHandler;
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
	
	IBlockLightHandler getLightProperties();
	
	IBlockStateHandler getBlockStateHandler();
	
	IBlockPhysicsHandler getPhysicsHandler();
	
	IBlockRenderHandler getRenderhandler();
	
	IBlockHarvestHandler getHarvestHandler();
	
	IBlockRedstoneHandler getRedstoneHandler();
	
	IBlockCreativeTabHandler getCreativeTabHandler();
	
	IItemStack toItemStack(IBlockState state);
	
}
