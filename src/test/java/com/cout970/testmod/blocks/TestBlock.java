package com.cout970.testmod.blocks;

import com.cout970.testmod.ModClass;
import com.cout970.testmod.tile.TileTestBlock;
import net.darkaqua.blacksmith.api.common.block.BlockMaterialFactory;
import net.darkaqua.blacksmith.api.common.block.IBlockContainerDefinition;
import net.darkaqua.blacksmith.api.common.block.IBlockMaterial;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.block.defaults.DefaultBlockDefinition;
import net.darkaqua.blacksmith.api.common.block.methods.BlockMethod;
import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.WorldRef;
import net.darkaqua.blacksmith.api.common.world.IWorld;

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
        player.openGui(ModClass.MOD_IDENTIFIER, 0, ref);
        return false;
    }
}
