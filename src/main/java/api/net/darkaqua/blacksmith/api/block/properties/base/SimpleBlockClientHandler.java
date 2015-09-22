package net.darkaqua.blacksmith.api.block.properties.base;

import java.util.List;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockState;
import net.darkaqua.blacksmith.api.block.properties.IBlockClientHandler;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.inventory.ItemStackFactory;
import net.darkaqua.blacksmith.api.item.Item;
import net.darkaqua.blacksmith.api.util.BlockPos;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.world.IBlockAccess;
import net.darkaqua.blacksmith.api.world.World;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.MovingObjectPosition;

public class SimpleBlockClientHandler implements IBlockClientHandler{

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
	public IItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) {
		return getBlock().toItemStack(world.getBlockState(pos));
	}

	@Override
	public boolean addDestroyEffects(World world, BlockPos pos, EffectRenderer effectRenderer) {
		return false;
	}

	@Override
	public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
		return false;
	}

	@Override
	public Color getBlockColor() {
		return new Color(0xFFFFFF);
	}

	@Override
	public Color getRenderColor(IBlockState state) {
		return new Color(0xFFFFFF);
	}

	@Override
	public Color colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
		return new Color(0xFFFFFF);
	}

	@Override
	public int getBlockRenderLayer() {
		return 0;//DOTO change this for an enum
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, Direction side) {
		return true;//TODO fix this method when IBlockAcces allow to get a block
	}

	@Override
	public Cube getSelectionBox() {
		return getBlock().getPhysicsHandler().getBlockBounds();
	}

	@Override
	public IBlockState getStateForEntityRender(IBlockState state) {
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
}
