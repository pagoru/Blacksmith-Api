package net.darkaqua.blacksmith.api.block.properties;

import java.util.List;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockState;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.Item;
import net.darkaqua.blacksmith.api.util.BlockPos;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.world.IBlockAccess;
import net.darkaqua.blacksmith.api.world.World;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.MovingObjectPosition;

public interface IBlockClientHandler {
	
	IBlock getBlock();

	CreativeTabs getCreativeTab();
	
	void getSubBlocks(Item itemIn, CreativeTabs tab, List<IItemStack> list);
	
	IItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos);
	
	boolean addDestroyEffects(World world, BlockPos pos, net.minecraft.client.particle.EffectRenderer effectRenderer);
	
	boolean addHitEffects(World worldObj, MovingObjectPosition target, net.minecraft.client.particle.EffectRenderer effectRenderer);
	
	Color getBlockColor();
	
	Color getRenderColor(IBlockState state);
	
	Color colorMultiplier(IBlockAccess world, BlockPos pos, int renderPass);
	
	//TODO change int to an enum
	int getBlockRenderLayer();
	
	boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, Direction side);
	
	boolean isTransparent();
	
	Cube getSelectionBox();
	
	float getAmbientOcclusionLightValue();
	
	IBlockState getStateForEntityRender(IBlockState state);
}
