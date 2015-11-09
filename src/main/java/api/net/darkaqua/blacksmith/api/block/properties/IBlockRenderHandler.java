package net.darkaqua.blacksmith.api.block.properties;

import java.util.List;
import java.util.Random;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vector3i;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.world.IIBlockAccess;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.MovingObjectPosition;

public interface IBlockRenderHandler {

	IBlock getBlock();

	CreativeTabs getCreativeTab();

	void getSubBlocks(IItem itemIn, CreativeTabs tab, List<IItemStack> list);

	boolean addDestroyEffects(WorldRef ref, net.minecraft.client.particle.EffectRenderer effectRenderer);

	boolean addHitEffects(IWorld worldObj, MovingObjectPosition target, net.minecraft.client.particle.EffectRenderer effectRenderer);

	Color getBlockColor();

	Color getRenderColor(IIBlockState state);

	Color colorMultiplier(IIBlockAccess world, Vector3i pos, int renderPass);

	// TODO change int to an enum
	int getBlockRenderLayer();

	boolean shouldSideBeRendered(IIBlockAccess world, Vector3i pos, Direction side);
	
	void randomRenderTick(WorldRef ref, IIBlockState state, Random rand);

	boolean isTransparent();

	float getAmbientOcclusionLightValue();

	IIBlockState getStateForEntityRender(IIBlockState state);
}
