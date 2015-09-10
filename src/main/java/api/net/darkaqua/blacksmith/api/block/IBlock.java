package net.darkaqua.blacksmith.api.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IWorldAccess;

/**
 * 
 * @author cout970
 *
 */
public interface IBlock {

	/**
	 * 
	 * @return The internal block name in the form: tile.blockname.name
	 */
	String getUnlocalizedName();
	
	String getLocalizedName();
	
	/**
	 * @return if the block is complete or not, used in slabs and stairs
	 */
	boolean isCompleteBlock();
	
	/**
	 * values from 0 to 255 inclusive
	 * @return the amount of light that is absorbed by the block
	 */
	int getOpacity();
	
	
	boolean isTranslucent();
	
	/**
	 * values from 0 to 15 inclusive
	 * @return the amount of light that this block emits
	 */
	int getLightEmited();
	
	IBlockState getStateFromMeta(int meta);
	
	int getMetaFromState(IBlockState state);
	
	IBlockState getActualState(IBlockState state, IWorldAccess worldIn, BlockPos pos);
}
