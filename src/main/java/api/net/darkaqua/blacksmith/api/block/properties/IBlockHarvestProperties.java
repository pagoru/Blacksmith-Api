package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.blockstate.IBlockVariant;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.ToolType;
import net.darkaqua.blacksmith.api.util.ClientSideOnly;
import net.darkaqua.blacksmith.api.util.Vector3i;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.world.IIBlockAccess;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;

import java.util.List;

public interface IBlockHarvestProperties {
	
	IBlock getBlock();

	boolean isToolEffective(ToolType type, IBlockVariant state);
	
	//TODO change int to an enum
	int getHarvestLevel(IBlockVariant state);
	
	ToolType getHarvestTool(IBlockVariant state);
	
	int getExpDrop(IIBlockAccess world, Vector3i pos, int fortune);
	
	boolean canEntityDestroy(IIBlockAccess world, Vector3i pos, IEntity entity);
		
	boolean canSilkHarvest(WorldRef ref, IBlockVariant state, EntityPlayer player);
	
	boolean canHarvestBlock(IIBlockAccess world, Vector3i pos, EntityPlayer player);
	
	boolean canDropFromExplosion(Explosion explosionIn);
	
	boolean isReplaceable(WorldRef ref);
	
	float getBlockHardness(WorldRef ref);
	
	float getBlockHardness(EntityPlayer player, WorldRef ref);
	
	float getExplosionResistance(WorldRef ref, IEntity exploder, Explosion explosion);
	
	List<IItemStack> getDrops(IIBlockAccess world, Vector3i pos, IBlockVariant state, int fortune);
	
	@ClientSideOnly
	IItemStack getPickBlock(MovingObjectPosition target, WorldRef ref);
}
