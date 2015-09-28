package net.darkaqua.blacksmith.api.block.properties.base;

import java.util.ArrayList;
import java.util.List;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.block.properties.IBlockHarvestHandler;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.ToolType;
import net.darkaqua.blacksmith.api.util.BlockLoc;
import net.darkaqua.blacksmith.api.world.IBlockAccess;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;

public class SimpleBlockHarvestHandler implements IBlockHarvestHandler {

	protected IBlock block;
	protected ToolType harvestTool;
	protected float blockHardness;
	protected float blockResistance;

	public SimpleBlockHarvestHandler(IBlock block) {
		this.block = block;
		setBlockHarvestTool(new ToolType("pickaxe"));
		setBlockHardness(2f);
		setBlockResistance(10f);
	}

	protected SimpleBlockHarvestHandler setBlockUnbreakable() {
		setBlockHardness(-1f);
		return this;
	}

	protected SimpleBlockHarvestHandler setBlockHardness(float hardness) {
		blockHardness = hardness;
		if (blockResistance < hardness * 5.0F) {
			blockResistance = hardness * 5.0F;
		}
		return this;
	}

	protected SimpleBlockHarvestHandler setBlockResistance(float resistance) {
		blockResistance = resistance;
		return this;
	}

	protected SimpleBlockHarvestHandler setBlockHarvestTool(ToolType type) {
		harvestTool = type;
		return this;
	}

	@Override
	public IBlock getBlock() {
		return block;
	}

	@Override
	public boolean isToolEffective(ToolType type, IIBlockState state) {
		return type.equals(getHarvestTool(state));
	}

	@Override
	public int getHarvestLevel(IIBlockState state) {
		return 0;
	}

	@Override
	public ToolType getHarvestTool(IIBlockState state) {
		return harvestTool;
	}

	@Override
	public int getExpDrop(IBlockAccess world, BlockLoc pos, int fortune) {
		return 0;
	}

	@Override
	public boolean canEntityDestroy(IBlockAccess world, BlockLoc pos, IEntity entity) {
		return true;
	}

	@Override
	public boolean canSilkHarvest(IWorld world, BlockLoc pos, IIBlockState state, EntityPlayer player) {
		return false;
	}


	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockLoc pos, EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return true;
	}

	@Override
	public boolean isReplaceable(IWorld world, BlockLoc pos) {
		return false;
	}

	@Override
	public float getBlockHardness(IWorld world, BlockLoc pos) {
		return blockHardness;
	}

	@Override
	public float getBlockHardness(EntityPlayer player, IWorld world, BlockLoc pos) {
		//TODO add diferent block hardness depending on the player's tool.
		return getBlockHardness(world, pos);
	}

	@Override
	public float getExplosionResistance(IWorld world, BlockLoc pos, IEntity exploder, Explosion explosion) {
		return blockResistance;
	}

	@Override
	public List<IItemStack> getDrops(IBlockAccess world, BlockLoc pos, IIBlockState state, int fortune) {
		List<IItemStack> list = new ArrayList<IItemStack>();
		list.add(getBlock().toItemStack(state));
		return list;
	}

	@Override
	public IItemStack getPickBlock(MovingObjectPosition target, IWorld world, BlockLoc pos) {
		return getBlock().toItemStack(world.getBlockState(pos));
	}
}
