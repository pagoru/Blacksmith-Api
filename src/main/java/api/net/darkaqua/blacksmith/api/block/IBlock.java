package net.darkaqua.blacksmith.api.block;

import net.darkaqua.blacksmith.api.block.properties.*;
import net.darkaqua.blacksmith.api.item.IItem;

/**
 * This is an abstraction to a Minecraft block
 * This must not be implemented
 *
 * @author cout970
 *
 */
public interface IBlock {

	/**
	 * @return The internal block name in the form: tile.blockname.name
	 */
	String getUnlocalizedName();

	/**
	 * @return The localized name of the block
     */
	String getLocalizedName();

	// Different handlers for different aspects of the block

	/**
	 * Light Related stuff
     */
	IBlockLightHandler getLightHandler();

	/**
	 * Block States and metadata related stuff
     */
	IBlockStateHandler getStateHandler();

	/**
	 * Different properties about the block structure or about collision boxes
     */
	IBlockPhysicsHandler getPhysicsHandler();

	/**
	 * Stuff related with block drops and block harvesting
     */
	IBlockHarvestHandler getHarvestHandler();

	/**
	 * Stuff related with redstone
     */
	IBlockRedstoneHandler getRedstoneHandler();

	/**
	 * Stuff related with render and client only things
     */
	IBlockRenderHandler getRenderHandler();

	/**
	 * Stuff about TileEntities
     */
	IBlockTileEntityHandler getTileEntityHandler();

	/**
	 * Stuff about blocks random tick
     */
	IBlockTickHandler getTickHandler();

	/**
	 * Stuff about events like right click, on break or on added
     */
	IBlockEventHandler getEventHandler();

	/**
	 * The item that represents this block on an inventory
     */
	IItem getItemBlock();

	/**
	 * The internal minecraft block
	 * Useful to check interfaces and apis outside Blacksmith
     */
	Object getInternalBlock();
}
