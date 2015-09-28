package net.darkaqua.blacksmith.mod.block;

import java.util.List;
import java.util.Random;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Vector3d;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MCBlock extends Block {

	public IBlock block;

	protected MCBlock(IBlock block) {
		super(Material.iron);// TODO change material
		this.block = block;

		Cube cube = block.getPhysicsHandler().getBlockBounds();
		setBlockBounds((float) cube.minX(), (float) cube.minY(), (float) cube.minZ(), (float) cube.maxX(),
				(float) cube.maxY(), (float) cube.maxZ());
	}
	
	// NOTE continue in getBlockBoundsMinX()

	// Physics

	@Override
	public boolean isFullBlock() {
		return block.getPhysicsHandler().isFullBlock();
	}

	@Override
	public boolean isSolidFullCube() {
		return block.getPhysicsHandler().isSolidBlock();
	}

	@Override
	public boolean isNormalCube() {
		return block.getPhysicsHandler().isNormalBlock();
	}

	@Override
	public boolean isFullCube() {
		return block.getPhysicsHandler().isCompleteBlock();
	}

	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return block.getPhysicsHandler().isTraspasable(MCInterface.fromBlockAccess(worldIn),
				MCInterface.fromBlockPos(pos));
	}

	@Override
	public boolean isBlockSolid(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return block.getPhysicsHandler().isSideSolid(MCInterface.fromBlockAccess(worldIn),
				MCInterface.fromBlockPos(pos), MCInterface.fromEnumFacing(side));
	}

	@Override
	@SuppressWarnings("unchecked")
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, @SuppressWarnings("rawtypes") List list, Entity collidingEntity) {
		List<Cube> boxes = block.getPhysicsHandler().getCollidingCubes(MCInterface.fromWorld(worldIn),
				MCInterface.fromBlockPos(pos), MCInterface.fromBlockState(state),
				MCInterface.fromAxisAlignedBB(mask), MCInterface.fromEntity(collidingEntity));
		for (Cube box : boxes) {
			list.add(MCInterface.toAxisAlignedBB(box));
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
		return MCInterface
				.toAxisAlignedBB(block.getPhysicsHandler().getColisionBox(MCInterface.fromWorld(worldIn),
						MCInterface.fromBlockPos(pos), MCInterface.fromBlockState(state)));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
		return MCInterface.toAxisAlignedBB(block.getPhysicsHandler()
				.getSelectionBox(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)));
	}

	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return block.getPhysicsHandler().canCollide(MCInterface.fromBlockState(state), hitIfLiquid);
	}

	// Harvest

	@Override
	public boolean isReplaceable(World worldIn, BlockPos pos) {
		return block.getHarvestHandler().isReplaceable(MCInterface.fromWorld(worldIn),
				MCInterface.fromBlockPos(pos));
	}

	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer playerIn, World worldIn, BlockPos pos) {
		// return block.getHarvestHandler().getBlockHardness(playerIn, MCInterface.fromWorld(worldIn),
		// MCInterface.fromBlockPos(pos));
		return super.getPlayerRelativeBlockHardness(playerIn, worldIn, pos);
	}

	@Override
	public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {
		return block.getHarvestHandler().getExplosionResistance(MCInterface.fromWorld(world),
				MCInterface.fromBlockPos(pos), MCInterface.fromEntity(exploder), explosion);
	}

	@Override
	public boolean canReplace(World worldIn, BlockPos pos, EnumFacing side, ItemStack stack) {
		return block.getHarvestHandler().isReplaceable(MCInterface.fromWorld(worldIn),
				MCInterface.fromBlockPos(pos));
	}

	// Event

	@Override
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
		block.getEventHandler().onBlockDestroyedByExplosion(MCInterface.fromWorld(worldIn),
				MCInterface.fromBlockPos(pos), explosionIn);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		block.getEventHandler().onBlockAdded(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos),
				MCInterface.fromBlockState(state));
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		block.getEventHandler().onBlockBreaks(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos),
				MCInterface.fromBlockState(state));
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		return block.getEventHandler().onBlockActivated(MCInterface.fromWorld(worldIn),
				MCInterface.fromBlockPos(pos), MCInterface.fromBlockState(state), playerIn,
				MCInterface.fromEnumFacing(side), new Vector3d(hitX, hitY, hitZ));
	}

	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn) {
		block.getEventHandler().onEntityCollidedWithBlock(MCInterface.fromWorld(worldIn),
				MCInterface.fromBlockPos(pos), MCInterface.fromEntity(entityIn));
	}

	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return MCInterface.toBlockState(block.getEventHandler().onBlockPlaced(MCInterface.fromWorld(worldIn),
				MCInterface.fromBlockPos(pos), MCInterface.fromEnumFacing(facing),
				MCInterface.fromEntity(placer), new Vector3d(hitX, hitY, hitZ), meta));
	}

	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		block.getEventHandler().onBlockClicked(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos),
				playerIn);
	}

	// Light

	@Override
	public int getLightOpacity() {
		return (int) (block.getLightHandler().getLightOpacity() * 255f) & 255;
	}

	@Override
	public int getLightValue() {
		return (int) (block.getLightHandler().getLightEmited() * 15) & 15;
	}

	@Override
	public boolean isVisuallyOpaque() {
		return block.getLightHandler().isOpaque();
	}

	@Override
	public boolean isOpaqueCube() {
		return block.getLightHandler().isOpaque();
	}

	// Render

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isTranslucent() {
		return block.getRenderHandler().isTransparent();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return block.getRenderHandler().shouldSideBeRendered(MCInterface.fromBlockAccess(worldIn),
				MCInterface.fromBlockPos(pos), MCInterface.fromEnumFacing(side));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		block.getRenderHandler().randomRenderTick(MCInterface.fromWorld(worldIn),
				MCInterface.fromBlockPos(pos), MCInterface.fromBlockState(state), rand);
	}

	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.values()[block.getRenderHandler()
				.getBlockRenderLayer() % EnumWorldBlockLayer.values().length];
	}

	// State

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return MCInterface.toBlockState(block.getStateHandler().getStateFromMeta(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return block.getStateHandler().getMetaFromState(MCInterface.fromBlockState(state));
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MCInterface
				.toBlockState(block.getStateHandler().getActualState(MCInterface.fromBlockState(state),
						MCInterface.fromBlockAccess(worldIn), MCInterface.fromBlockPos(pos)));
	}

	// Tick

	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		block.getTickHandler().randomTick(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos),
				MCInterface.fromBlockState(state), random);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		block.getTickHandler().updateTick(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos),
				MCInterface.fromBlockState(state), rand);
	}

	@Override
	public int tickRate(World worldIn) {
		return block.getTickHandler().tickRate(MCInterface.fromWorld(worldIn));
	}

	// TODO

	@Override
	public Material getMaterial() {
		return this.blockMaterial;
	}

	@Override
	public MapColor getMapColor(IBlockState state) {
		return this.getMaterial().getMaterialMapColor();
	}

	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
	}

	@Override
	public void dropXpOnBlockBreak(World worldIn, BlockPos pos, int amount) {
		super.dropXpOnBlockBreak(worldIn, pos, amount);
	}

	@Override
	public MovingObjectPosition collisionRayTrace(World worldIn, BlockPos pos, Vec3 start, Vec3 end) {
		return super.collisionRayTrace(worldIn, pos, start, end);
	}
}
