package net.darkaqua.blacksmith.mod.block;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.blockstate.IIBlockState;
import net.darkaqua.blacksmith.api.block.properties.*;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.util.Vector3i;
import net.darkaqua.blacksmith.api.world.IIBlockAccess;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.Block;

public class BlockWrapper implements IBlock {

	private Block block;

	public BlockWrapper(Block block){
		this.block = block;
	}

	public Block getBlock(){
		return block;
	}

	@Override
	public String getUnlocalizedName() {
		return block.getUnlocalizedName();
	}

	@Override
	public String getLocalizedName() {
		return block.getLocalizedName();
	}

	@Override
	public IBlockLightProperties getLightProperties() {

		return new IBlockLightProperties() {
			@Override
			public IBlock getBlock() {
				return BlockWrapper.this;
			}

			@Override
			public float getLightOpacity() {
				return block.getLightOpacity()/15f;
			}

			@Override
			public float getLightOpacity(IIBlockAccess world, Vector3i pos) {
				return block.getLightOpacity(MCInterface.toBlockAccess(world), MCInterface.toBlockPos(pos))/15f;
			}

			@Override
			public float getLightEmitted() {
				return block.getLightValue();
			}

			@Override
			public boolean isOpaque() {
				return block.isOpaqueCube();
			}
		};
	}

	@Override
	public IBlockStateProperties getBlockStateProperties() {
		return new IBlockStateProperties() {
			@Override
			public IBlock getBlock() {
				return BlockWrapper.this;
			}

			@Override
			public IIBlockState getDefaultState() {
				return MCInterface.fromIBlockState(block.getDefaultState());
			}

			@Override
			public IIBlockState getActualState(IIBlockState state, IIBlockAccess worldIn, Vector3i pos) {
				return MCInterface.fromIBlockState(block.getActualState(MCInterface.toIBlockState(state), MCInterface.toBlockAccess(worldIn), MCInterface.toBlockPos(pos)));
			}

			@Override
			public IIBlockState getStateFromMeta(int meta) {
				return MCInterface.fromIBlockState(block.getStateFromMeta(meta));
			}

			@Override
			public int getMetaFromState(IIBlockState state) {
				return block.getMetaFromState(MCInterface.toIBlockState(state));
			}
		};
	}

	@Override
	public IBlockPhysicsProperties getPhysicsProperties() {
		return null;
	}

	@Override
	public IBlockHarvestProperties getHarvestProperties() {
		return null;
	}

	@Override
	public IBlockRedstoneProperties getRedstoneProperties() {
		return null;
	}

	@Override
	public IBlockRenderProperties getRenderProperties() {
		return null;
	}

	@Override
	public IBlockTileEntityProperties getTileEntityProperties() {
		return null;
	}

	@Override
	public IBlockTickProperties getTickProperties() {
		return null;
	}

	@Override
	public IBlockEventHandler getEventHandler() {
		return null;
	}

	@Override
	public IItem getItemBlock() {
		return MCInterface.fromItem(net.minecraft.item.Item.getItemFromBlock(block));
	}

	@Override
	public Object getInternalBlock() {
		return block;
	}
}
