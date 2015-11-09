package net.darkaqua.blacksmith.mod.block;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
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
	public IBlockLightHandler getLightHandler() {

		return new IBlockLightHandler() {
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
			public float getLightEmited() {
				return block.getLightValue();
			}

			@Override
			public boolean isOpaque() {
				return block.isOpaqueCube();
			}
		};
	}

	@Override
	public IBlockStateHandler getStateHandler() {
		return new IBlockStateHandler() {
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
	public IBlockPhysicsHandler getPhysicsHandler() {
		return null;
	}

	@Override
	public IBlockHarvestHandler getHarvestHandler() {
		return null;
	}

	@Override
	public IBlockRedstoneHandler getRedstoneHandler() {
		return null;
	}

	@Override
	public IBlockRenderHandler getRenderHandler() {
		return null;
	}

	@Override
	public IBlockTileEntityHandler getTileEntityHandler() {
		return null;
	}

	@Override
	public IBlockTickHandler getTickHandler() {
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
}
