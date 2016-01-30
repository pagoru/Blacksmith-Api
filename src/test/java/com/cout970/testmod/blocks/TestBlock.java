package com.cout970.testmod.blocks;

import com.cout970.testmod.ModClass;
import com.cout970.testmod.tile.TileTestBlock;
import net.darkaqua.blacksmith.api.block.BlockMaterialFactory;
import net.darkaqua.blacksmith.api.block.IBlockContainerDefinition;
import net.darkaqua.blacksmith.api.block.IBlockMaterial;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.block.defaults.DefaultBlockDefinition;
import net.darkaqua.blacksmith.api.block.methods.BlockMethod;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.world.IWorld;

/**
 * Created by cout970 on 24/11/2015.
 */
public class TestBlock extends DefaultBlockDefinition implements IBlockContainerDefinition, BlockMethod.OnActivated {

    public TestBlock() {
        super("testBlock");
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public float getLightOpacity(){
        return 0F;
    }

    @Override
    public IBlockMaterial getBlockMaterial() {
        return BlockMaterialFactory.IRON;
    }

    @Override
    public ITileEntityDefinition createTileEntity(IWorld world, IBlockData state) {
        return new TileTestBlock();
    }

    @Override
    public boolean onActivated(WorldRef ref, IBlockData data, IPlayer player, Direction side, Vect3d ray) {
        player.openGui(ModClass.instance, 0, ref);
        return false;
    }
}
