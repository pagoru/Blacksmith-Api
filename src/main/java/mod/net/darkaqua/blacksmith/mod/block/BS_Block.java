package net.darkaqua.blacksmith.mod.block;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.block.methods.BlockMethod;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by cout970 on 08/11/2015.
 */
public class BS_Block extends Block {

    protected IBlockDefinition definition;

    public BS_Block(IBlockDefinition def) {
        super(Material.rock);//Temp
        definition = def;
        this.fullBlock = this.isOpaqueCube();
        setUnlocalizedName(def.getUnlocalizedName());
        setCreativeTab(MCInterface.fromCreativeTab(def.getCreativeTab()));
        setBlockBounds(def.getBounds());
        setHardness(def.getHardness());
        setLightLevel(def.getLightEmitted());
        setLightOpacity((int) (def.getLightOpacity() * 255f));
        setResistance(def.getResistance());
        useNeighborBrightness = false;
    }

    public IBlockDefinition getBlockDefinition(){
        return definition;
    }

    public int getRenderType(){
        return definition.shouldRender() ? 3 : -1;
    }

    public boolean isOpaqueCube() {
        return definition == null || definition.isFullCube();
    }

    private void setBlockBounds(Cube c) {
        setBlockBounds((float) c.minX(), (float) c.minY(), (float) c.minZ(), (float) c.maxX(), (float) c.maxY(), (float) c.maxZ());
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (definition instanceof BlockMethod.OnActivated) {
            return ((BlockMethod.OnActivated) definition).onActivated(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromIBlockState(state), MCInterface.toPlayer(playerIn), MCInterface.fromEnumFacing(side), new Vect3d(hitX, hitY, hitZ));
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (definition instanceof BlockMethod.OnAdded) {
            ((BlockMethod.OnAdded) definition).onAdded(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromIBlockState(state));
        } else {
            super.onBlockAdded(worldIn, pos, state);
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

        if (definition instanceof BlockMethod.OnBreaks) {
            ((BlockMethod.OnBreaks) definition).onBreaks(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromIBlockState(state));
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
            ((BlockMethod.OnHarvested) definition).onHarvested(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromIBlockState(state), MCInterface.toPlayer(player));
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        if (definition instanceof BlockMethod.OnNeighborChange) {
            ((BlockMethod.OnNeighborChange) definition).onNeighborBlockChange(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromIBlockState(state), MCInterface.fromBlock(neighborBlock));
        }
        super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        if (definition instanceof BlockMethod.OnPlaced) {
            return MCInterface.toIBlockState(((BlockMethod.OnPlaced) definition).onPlaced(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromEnumFacing(facing), MCInterface.toLivingEntity(placer), new Vect3d(hitX, hitY, hitZ), meta));
        }
        return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (definition instanceof BlockMethod.OnPlacedBy) {
            ((BlockMethod.OnPlacedBy) definition).onPlacedBy(new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromIBlockState(state), MCInterface.toLivingEntity(placer), MCInterface.fromItemStack(stack));
        }
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        if (definition instanceof BlockMethod.OnRemovedByPlayer){
            return ((BlockMethod.OnRemovedByPlayer) definition).onRemovedByPlayer(new WorldRef(MCInterface.fromWorld(world), MCInterface.fromBlockPos(pos)), MCInterface.toPlayer(player), willHarvest);
        }
        return super.removedByPlayer(world, pos, player,willHarvest);
    }
}
