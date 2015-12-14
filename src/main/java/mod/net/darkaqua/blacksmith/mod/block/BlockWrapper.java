package net.darkaqua.blacksmith.mod.block;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.Block;

import javax.vecmath.Vector3d;

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
	public Cube getBlockBounds() {
		return new Cube(block.getBlockBoundsMinX(), block.getBlockBoundsMinY(), block.getBlockBoundsMinZ(),
				block.getBlockBoundsMaxX(), block.getBlockBoundsMaxY(), block.getBlockBoundsMaxZ());
	}

	@Override
	public float getHardness() {
		//TODO
		return block.getBlockHardness(null, null);
	}

	@Override
	public float getLightEmitted() {
		return block.getLightValue();
	}

	@Override
	public float getLightOpacity() {
		return block.getLightOpacity();
	}

	@Override
	public float getResistance() {
		//TODO
		return block.getExplosionResistance(null);
	}


	@Override
	public IItem getItemBlock() {
		return MCInterface.fromItem(net.minecraft.item.Item.getItemFromBlock(block));
	}

	@Override
	public boolean isOpaque() {
		return block.isOpaqueCube();
	}

	@Override
	public IBlockVariant getDefaultVariant() {
		return MCInterface.fromIBlockVariant(block.getDefaultState());
	}

	@Override
	public IBlockVariant getVariantFromMeta(int meta) {
		return MCInterface.fromIBlockVariant(block.getDefaultState());
	}

	@Override
	public int getMetaFromVariant(IBlockVariant variant) {
		return block.getMetaFromState(MCInterface.toIBlockState(variant));
	}

	@Override
	public ICreativeTab getCreativeTab() {
		return MCInterface.fromCreativeTab(block.getCreativeTabToDisplayOn());
	}

	@Override
	public Object getInternalBlock() {
		return block;
	}

	//TODO

	@Override
	public boolean onBlockActivated(WorldRef ref, IBlockVariant state, IPlayer player, Direction side, Vector3d vector3d) {
		return false;
	}

	@Override
	public void onBlockAdded(WorldRef ref, IBlockVariant fromBlockState) {

	}

	@Override
	public void onActivate() {

	}

	@Override
	public void onBlockBreaks(WorldRef ref, IBlockVariant state) {

	}

	@Override
	public void onBlockClicked(WorldRef ref, IPlayer player) {

	}

	@Override
	public void onEntityCollidedWithBlock(WorldRef ref, IEntity entity) {

	}

	@Override
	public void onBlockHarvested(WorldRef ref, IBlockVariant variant, IPlayer player) {

	}

	@Override
	public void onNeighborBlockChange(WorldRef ref, IBlockVariant state, IBlock neighbor) {

	}

	@Override
	public IBlockVariant onBlockPlaced(WorldRef ref, Direction side, IPlayer entity, Vector3d hit, int metadata) {
		return null;
	}

	@Override
	public void onBlockPlacedBy(WorldRef ref, IBlockVariant state, IPlayer placer, IItemStack stack) {

	}

	@Override
	public boolean removedByPlayer(WorldRef ref, IPlayer player, boolean willHarvest) {
		return false;
	}
}
