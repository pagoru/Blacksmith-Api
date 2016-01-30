package com.cout970.testmod.blocks;

import com.cout970.testmod.tile.TileTestBlock;
import net.darkaqua.blacksmith.api.block.BlockMaterialFactory;
import net.darkaqua.blacksmith.api.block.IBlockContainerDefinition;
import net.darkaqua.blacksmith.api.block.IBlockMaterial;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.block.defaults.DefaultBlockDefinition;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.world.IWorld;

/**
 * Created by cout970 on 24/11/2015.
 */
public class TestBlock extends DefaultBlockDefinition implements IBlockContainerDefinition {

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
}
