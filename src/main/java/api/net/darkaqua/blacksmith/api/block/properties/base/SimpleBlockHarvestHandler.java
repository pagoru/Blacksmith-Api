package net.darkaqua.blacksmith.api.block.properties.base;

import java.util.ArrayList;
import java.util.List;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockState;
import net.darkaqua.blacksmith.api.block.properties.IBlockHarvestHandler;
import net.darkaqua.blacksmith.api.entity.Entity;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.ToolType;
import net.darkaqua.blacksmith.api.util.BlockPos;
import net.darkaqua.blacksmith.api.world.IBlockAccess;
import net.darkaqua.blacksmith.api.world.World;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Explosion;

public class SimpleBlockHarvestHandler implements IBlockHarvestHandler{

	protected IBlock block;
	private ToolType harvestTool;
	
	public SimpleBlockHarvestHandler(IBlock block){
		this.block = block;
		harvestTool = new ToolType("pickaxe");
	}
	
	@Override
	public IBlock getBlock() {
		return block;
	}

	@Override
	public boolean isToolEffective(ToolType type, IBlockState state) {
		return type.equals(getHarvestTool(state));
	}

	@Override
	public int getHarvestLevel(IBlockState state) {
		return 0;
	}

	@Override
	public ToolType getHarvestTool(IBlockState state) {
		return harvestTool;
	}

	@Override
	public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune) {
		return 0;
	}

	@Override
	public boolean canEntityDestroy(IBlockAccess world, BlockPos pos, Entity entity) {
		return true;
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		return false;
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return true;
	}

	@Override
	public boolean isReplaceable(World world, BlockPos pos) {
		return false;
	}

	@Override
	public float getBlockHardness(World world, BlockPos pos) {
		return 2f;
	}

	@Override
	public float getBlockHardness(EntityPlayer player, World world, BlockPos pos) {
		return getBlockHardness(world, pos);
	}

	@Override
	public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {
		return 10;
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {		
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, IItemStack stack) {		
	}

	@Override
	public List<IItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<IItemStack> list = new ArrayList<IItemStack>();
		list.add(getBlock().toItemStack(state));
		return list;
	}

}
