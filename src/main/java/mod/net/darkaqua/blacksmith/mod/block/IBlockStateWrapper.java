package net.darkaqua.blacksmith.mod.block;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.state.IBlockState;

import java.util.Map;

/**
 * Created by cout970 on 08/11/2015.
 */
public class IBlockStateWrapper implements IIBlockState{

    private IBlockState state;

    public IBlockStateWrapper(IBlockState state){
        this.state = state;
    }

    public IBlockState getIBlockState(){
        return state;
    }

    @Override
    public IBlock getBlock() {
        return MCInterface.fromBlock(state.getBlock());
    }

    @Override
    public Object getInternalBlockState() {
        return state;
    }

    @Override
    public Map<Object, Object> getProperties() {
        return state.getProperties();
    }
}
