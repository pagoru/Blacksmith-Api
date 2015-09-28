package net.darkaqua.blacksmith.api.block.properties.base;

import java.util.List;
import java.util.Random;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.block.properties.IBlockRenderHandler;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.inventory.ItemStackFactory;
import net.darkaqua.blacksmith.api.item.Item;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.world.IBlockAccess;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.MovingObjectPosition;

public class SimpleBlockClientHandler implements IBlockRenderHandler{

	protected IBlock block;

	public SimpleBlockClientHandler(IBlock block){
		this.block = block;
	}
	
	@Override
	public IBlock getBlock() {
		return block;
	}

	@Override
	public CreativeTabs getCreativeTab() {
		return null;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<IItemStack> list) {
		list.add(ItemStackFactory.create(item, 1, 0));
	}

	@Override
	public boolean addDestroyEffects(IWorld world, BlockLoc pos, EffectRenderer effectRenderer) {
		return false;
	}

	@Override
	public boolean addHitEffects(IWorld worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
		return false;
	}

	@Override
	public Color getBlockColor() {
		return new Color(0xFFFFFF);
	}

	@Override
	public Color getRenderColor(IIBlockState state) {
		return new Color(0xFFFFFF);
	}

	@Override
	public Color colorMultiplier(IBlockAccess worldIn, BlockLoc pos, int renderPass) {
		return new Color(0xFFFFFF);
	}

	@Override
	public int getBlockRenderLayer() {
		return 0;//DOTO change this for an enum
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, BlockLoc pos, Direction side) {
		return true;//TODO fix this method when IBlockAcces allow to get a block
	}

	@Override
	public IIBlockState getStateForEntityRender(IIBlockState state) {
		return state;
	}
	
	@Override
	public float getAmbientOcclusionLightValue() {
		return getBlock().getLightHandler().isOpaque() ? 0.2F : 1.0F;
	}

	@Override
	public boolean isTransparent() {
		return false;
	}

	@Override
	public void randomRenderTick(IWorld world, BlockLoc pos, IIBlockState state, Random rand) {}
}
