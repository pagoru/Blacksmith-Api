package net.darkaqua.blacksmith.mod.common.block;

import net.darkaqua.blacksmith.api.common.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.block.methods.BlockMethod;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.util.raytrace.Cube;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.WorldRef;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 08/11/2015.
 */
public class BS_Block extends Block {

    protected IBlockDefinition definition;
    protected BlockState blockstate;

    public BS_Block(IBlockDefinition def) {
        super(MCInterface.toMaterial(def.getBlockMaterial()));
        definition = def;
        def.onCreate(MCInterface.fromBlock(this));
        this.fullBlock = this.isOpaqueCube();
        setUnlocalizedName(def.getUnlocalizedName());
        setCreativeTab(MCInterface.fromCreativeTab(def.getCreativeTab()));
        setLightOpacity((int)(def.getLightOpacity()* 255));
        setLightLevel((int)(def.getLightEmitted()* 15));
        setBlockBounds(0, 0, 0, 1, 1, 1);
        useNeighborBrightness = false;
        blockstate = MCInterface.fromBlockDataGenerator(definition.getBlockDataGenerator());
        IBlockData data = def.onCreateDefaultBlockData(MCInterface.fromBlockState(blockstate.getBaseState()));
        this.setDefaultState(MCInterface.toBlockState(data));
    }

    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MCInterface.fromCreativeTab(definition.getCreativeTab());
    }

    public int getLightValue(IBlockAccess world, BlockPos pos) {
        return (int)(definition.getLightEmitted(MCInterface.fromBlockAccess(world), MCInterface.fromBlockPos(pos)) * 15f);
    }

    public int getLightOpacity(IBlockAccess world, BlockPos pos){
        return (int)(definition.getLightOpacity(MCInterface.fromBlockAccess(world), MCInterface.fromBlockPos(pos))* 255f);
    }

    public MovingObjectPosition collisionRayTrace(World worldIn, BlockPos pos, Vec3 start, Vec3 end) {
        return MCInterface.toMOP(definition.rayTraceBlock(MCInterface.toWorldRef(worldIn, pos), MCInterface.fromVec3(start), MCInterface.fromVec3(end)));
    }

    public boolean isFullCube() {
        return definition.isFullCube();
    }

    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {
        return definition.getExplosionResistance(MCInterface.toWorldRef(world, pos), MCInterface.fromEntity(exploder));
    }

    public float getBlockHardness(World worldIn, BlockPos pos) {
        return definition.getHardness(MCInterface.toWorldRef(worldIn, pos));
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
        return MCInterface.toAxisAlignedBB(definition.getSelectionCube(MCInterface.toWorldRef(worldIn, pos)).translate(MCInterface.fromBlockPos(pos).toVect3d()));
    }

    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity) {
        List<Cube> boxes = definition.getCollisionCubes(MCInterface.toWorldRef(worldIn, pos), MCInterface.fromEntity(collidingEntity));
        Vect3d vec = MCInterface.fromBlockPos(pos).toVect3d();
        boxes.stream().map((c) -> c.translate(vec)).map(MCInterface::toAxisAlignedBB).filter(mask::intersectsWith).forEach(list::add);
    }

    @Override
    public BlockState getBlockState() {
        return blockstate;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return MCInterface.toBlockState(definition.translateMetadataToVariant(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return definition.translateVariantToMetadata(MCInterface.fromBlockState(state));
    }

    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return !this.blockMaterial.blocksMovement() || !isFullBlock();
    }

    public IBlockDefinition getBlockDefinition() {
        return definition;
    }

    @Override
    public int getRenderType() {
        return definition.shouldRender() ? 3 : -1;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        List<IItemStack> list2 = new LinkedList<>();
        definition.getSubBlocks(MCInterface.fromItem(itemIn), MCInterface.fromCreativeTab(tab), list2);
        list2.stream().map(MCInterface::toItemStack).forEach(list::add);
    }

    @Override
    public boolean isOpaqueCube() {
        return definition == null || definition.isFullCube();
    }

    @Override
    public boolean isBlockNormalCube() {
        return definition.isFullCube();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (definition instanceof BlockMethod.OnActivated) {
            return ((BlockMethod.OnActivated) definition).onActivated(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromBlockState(state), MCInterface.toPlayer(playerIn), MCInterface.fromEnumFacing(side), new Vect3d(hitX, hitY, hitZ));
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (definition instanceof BlockMethod.OnAdded) {
            ((BlockMethod.OnAdded) definition).onAdded(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromBlockState(state));
        } else {
            super.onBlockAdded(worldIn, pos, state);
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

        if (definition instanceof BlockMethod.OnBreaks) {
            ((BlockMethod.OnBreaks) definition).onBreaks(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromBlockState(state));
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        if (definition instanceof BlockMethod.OnClicked) {
            ((BlockMethod.OnClicked) definition).onClicked(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.toPlayer(playerIn));
        }
        super.onBlockClicked(worldIn, pos, playerIn);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn) {
        if (definition instanceof BlockMethod.OnEntityCollided) {
            ((BlockMethod.OnEntityCollided) definition).onEntityCollided(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromEntity(entityIn));
        }
        super.onEntityCollidedWithBlock(worldIn, pos, entityIn);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (definition instanceof BlockMethod.OnHarvested) {
            ((BlockMethod.OnHarvested) definition).onHarvested(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromBlockState(state), MCInterface.toPlayer(player));
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        if (definition instanceof BlockMethod.OnNeighborChange) {
            ((BlockMethod.OnNeighborChange) definition).onNeighborBlockChange(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromBlockState(state), MCInterface.fromBlock(neighborBlock));
        }
        super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        if (definition instanceof BlockMethod.OnPlaced) {
            return MCInterface.toBlockState(((BlockMethod.OnPlaced) definition).onPlaced(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromEnumFacing(facing), MCInterface.toLivingEntity(placer), new Vect3d(hitX, hitY, hitZ), meta));
        }
        return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (definition instanceof BlockMethod.OnPlacedBy) {
            ((BlockMethod.OnPlacedBy) definition).onPlacedBy(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromBlockState(state), MCInterface.toLivingEntity(placer), MCInterface.fromItemStack(stack));
        }
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        if (definition instanceof BlockMethod.OnRemovedByPlayer) {
            return ((BlockMethod.OnRemovedByPlayer) definition).onRemovedByPlayer(new WorldRef(MCInterface.fromWorld(world), MCInterface.fromBlockPos(pos)), MCInterface.toPlayer(player), willHarvest);
        }
        return super.removedByPlayer(world, pos, player, willHarvest);
    }
}
