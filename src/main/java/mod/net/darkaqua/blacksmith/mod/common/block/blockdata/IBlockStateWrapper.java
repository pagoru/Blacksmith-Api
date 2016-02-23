package net.darkaqua.blacksmith.mod.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttribute;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockDataHandler;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.block.state.IBlockState;

import static net.darkaqua.blacksmith.mod.common.util.MCInterface.fromBlockAttribute;

/**
 * Created by cout970 on 15/01/2016.
 */
public class IBlockStateWrapper implements IBlockData {

    private IBlockState state;

    public IBlockStateWrapper(IBlockState state) {
        this.state = state;
    }

    public IBlockState getBlockState() {
        return state;
    }

    @Override
    public IBlock getBlock() {
        return MCInterface.fromBlock(state.getBlock());
    }

    @Override
    public IBlockDataHandler getBlockDataHandler() {
        return MCInterface.toBlockDataHandler(state.getBlock().getBlockState());
    }

    @Override
    public <T extends Comparable<T>> T getValue(IBlockAttribute<T> attr) {
        return state.getValue(fromBlockAttribute(attr));
    }

    @Override
    public Object getInternalBlockState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IBlockStateWrapper)) return false;

        IBlockStateWrapper that = (IBlockStateWrapper) o;

        return !(state != null ? !state.equals(that.state) : that.state != null);

    }

    @Override
    public int hashCode() {
        return state != null ? state.hashCode() : 0;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
