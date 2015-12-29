package net.darkaqua.blacksmith.mod.block;

import net.darkaqua.blacksmith.api.block.IBlockContainerDefinition;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.mod.registry.TileEntityRegistry;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by cout970 on 08/11/2015.
 */
public class BS_BlockContainer extends BS_Block implements ITileEntityProvider {

    public BS_BlockContainer(IBlockContainerDefinition def) {
        super(def);
        this.isBlockContainer = true;
    }

    //Block Container methods, obfuscated, i don't know what they do
    protected boolean func_181086_a(World p_181086_1_, BlockPos p_181086_2_, EnumFacing p_181086_3_)
    {
        return p_181086_1_.getBlockState(p_181086_2_.offset(p_181086_3_)).getBlock().getMaterial() == Material.cactus;
    }

    protected boolean func_181087_e(World p_181087_1_, BlockPos p_181087_2_)
    {
        return this.func_181086_a(p_181087_1_, p_181087_2_, EnumFacing.NORTH) || this.func_181086_a(p_181087_1_, p_181087_2_, EnumFacing.SOUTH) || this.func_181086_a(p_181087_1_, p_181087_2_, EnumFacing.WEST) || this.func_181086_a(p_181087_1_, p_181087_2_, EnumFacing.EAST);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        ITileEntityDefinition tile = ((IBlockContainerDefinition)definition).createTileEntity(MCInterface.fromWorld(worldIn), MCInterface.fromIBlockState(getStateFromMeta(meta)));
        return MCInterface.toTileEntity(TileEntityRegistry.INSTANCE.createTileEntity(tile));
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }

    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam){
        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity != null && tileentity.receiveClientEvent(eventID, eventParam);
    }
}
