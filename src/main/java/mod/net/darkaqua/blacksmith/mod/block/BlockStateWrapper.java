package net.darkaqua.blacksmith.mod.block;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.block.IBlockVariantCreator;
import net.darkaqua.blacksmith.api.block.IIProperty;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cout970 on 29/12/2015.
 */
public class BlockStateWrapper implements IBlockVariantCreator{

    private BlockState state;

    public BlockStateWrapper(BlockState state) {
        this.state = state;
    }

    public BlockState getBlockState() {
        return state;
    }

    @Override
    public List<IBlockVariant> getValidVariants() {
        List<IBlockVariant> list = new ArrayList<>(16);
        for(IBlockState s : state.getValidStates()){
            list.add(MCInterface.fromIBlockState(s));
        }
        return list;
    }

    @Override
    public IBlockVariant getBaseVariant() {
        return MCInterface.fromIBlockState(state.getBaseState());
    }

    @Override
    public IBlock getBlock() {
        return MCInterface.fromBlock(state.getBlock());
    }

    @Override
    public List<IIProperty> getProperties() {
        List<IIProperty> list = new ArrayList<>(16);
        for(IProperty s : state.getProperties()){
            list.add(MCInterface.fromIProperty(s));
        }
        return list;
    }
}
