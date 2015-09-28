package net.darkaqua.blacksmith.api.block.properties;

import java.util.List;
import java.util.Random;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.Item;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.world.IBlockAccess;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.MovingObjectPosition;

public interface IBlockRenderHandler {

	IBlock getBlock();

	CreativeTabs getCreativeTab();

	void getSubBlocks(Item itemIn, CreativeTabs tab, List<IItemStack> list);

	boolean addDestroyEffects(IWorld world, BlockLoc pos, net.minecraft.client.particle.EffectRenderer effectRenderer);

	boolean addHitEffects(IWorld worldObj, MovingObjectPosition target, net.minecraft.client.particle.EffectRenderer effectRenderer);

	Color getBlockColor();

	Color getRenderColor(IIBlockState state);

	Color colorMultiplier(IBlockAccess world, BlockLoc pos, int renderPass);

	// TODO change int to an enum
	int getBlockRenderLayer();

	boolean shouldSideBeRendered(IBlockAccess world, BlockLoc pos, Direction side);
	
	void randomRenderTick(IWorld world, BlockLoc pos, IIBlockState state, Random rand);

	boolean isTransparent();

	float getAmbientOcclusionLightValue();

	IIBlockState getStateForEntityRender(IIBlockState state);
}
