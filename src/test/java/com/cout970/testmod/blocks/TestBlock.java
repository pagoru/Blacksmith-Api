package com.cout970.testmod.blocks;

import com.cout970.testmod.ModClass;
import net.darkaqua.blacksmith.api.block.BlockMaterialFactory;
import net.darkaqua.blacksmith.api.block.IBlockMaterial;
import net.darkaqua.blacksmith.api.block.defaults.DefaultBlockDefinition;
import net.darkaqua.blacksmith.api.block.methods.BlockMethod;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.registry.StaticAccess;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 24/11/2015.
 */
public class TestBlock extends DefaultBlockDefinition implements BlockMethod.OnActivated {

    public TestBlock() {
        super("testBlock");
    }

    @Override
    public boolean onActivated(WorldRef ref, IBlockData state, IPlayer player, Direction side, Vect3d vector3d) {
        StaticAccess.GAME.getGuiRegistry().openGui(player, ref, 0, ModClass.instance);
        return true;
    }

    @Override
    public IBlockMaterial getBlockMaterial() {
        return BlockMaterialFactory.IRON;
    }
}
