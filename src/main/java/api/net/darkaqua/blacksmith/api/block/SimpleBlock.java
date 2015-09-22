package net.darkaqua.blacksmith.api.block;

import net.darkaqua.blacksmith.api.block.properties.IBlockClientHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockHarvestHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockLightHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockPhysicsHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockRedstoneHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockStateHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockTickHandler;
import net.darkaqua.blacksmith.api.block.properties.IBlockTileEntityHandler;
import net.darkaqua.blacksmith.api.block.properties.base.SimpleBlockClientHandler;
import net.darkaqua.blacksmith.api.block.properties.base.SimpleBlockHarvestHandler;
import net.darkaqua.blacksmith.api.block.properties.base.SimpleBlockLightHandler;
import net.darkaqua.blacksmith.api.block.properties.base.SimpleBlockPhysicsHandler;
import net.darkaqua.blacksmith.api.block.properties.base.SimpleBlockStateHandler;
import net.darkaqua.blacksmith.api.block.properties.base.SimpleBlockTickHandler;
import net.darkaqua.blacksmith.api.block.properties.base.SimpleBlockTileEntityHandler;
import net.darkaqua.blacksmith.api.block.properties.base.SinmpleBlockRedstoneHandler;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.inventory.ItemStackFactory;

public class SimpleBlock implements IBlock{

	private String blockName;
	protected IBlockLightHandler lightHandler;
	protected IBlockStateHandler stateHandler;
	protected IBlockPhysicsHandler physicsHandler;
	protected IBlockHarvestHandler harvestHandler;
	protected IBlockRedstoneHandler redstoneHandler;
	protected IBlockClientHandler clientHandler;
	protected IBlockTileEntityHandler tileEntityHandler;
	protected IBlockTickHandler tickHandler;
	
	public SimpleBlock(){
		initBlock();
	}
	
	protected void setBlockName(String name){
		blockName = name;
	}
	
	public void initBlock(){
		lightHandler = new SimpleBlockLightHandler(this);
		stateHandler = new SimpleBlockStateHandler(this);
		physicsHandler = new SimpleBlockPhysicsHandler(this);
		harvestHandler = new SimpleBlockHarvestHandler(this);
		redstoneHandler = new SinmpleBlockRedstoneHandler(this);
		clientHandler = new SimpleBlockClientHandler(this);
		tileEntityHandler = new SimpleBlockTileEntityHandler(this);
		tickHandler = new SimpleBlockTickHandler(this);
	}
	
	@Override
	public String getUnlocalizedName() {
		return "tile."+blockName;
	}

	@Override
	public String getLocalizedName() {
		return null;//Implement a way to translate names
	}

	@Override
	public IBlockLightHandler getLightHandler() {
		return lightHandler;
	}

	@Override
	public IBlockStateHandler getStateHandler() {
		return stateHandler;
	}

	@Override
	public IBlockPhysicsHandler getPhysicsHandler() {
		return physicsHandler;
	}

	@Override
	public IBlockHarvestHandler getHarvestHandler() {
		return harvestHandler;
	}

	@Override
	public IBlockRedstoneHandler getRedstoneHandler() {
		return redstoneHandler;
	}

	@Override
	public IBlockClientHandler getClientHandler() {
		return clientHandler;
	}

	@Override
	public IBlockTileEntityHandler getTileEntityHandler() {
		return tileEntityHandler;
	}

	@Override
	public IBlockTickHandler getTickHandler() {
		return tickHandler;
	}

	@Override
	public IItemStack toItemStack(IBlockState state) {
		return ItemStackFactory.create(this);
	}

}
