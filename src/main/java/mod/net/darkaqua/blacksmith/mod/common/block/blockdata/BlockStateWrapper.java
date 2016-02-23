package net.darkaqua.blacksmith.mod.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttribute;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockDataHandler;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockStateWrapper implements IBlockDataHandler {

    private BlockState state;

    public BlockStateWrapper(BlockState state) {
        this.state = state;
    }

    public BlockState getBlockState() {
        return state;
    }

    @Override
    public List<IBlockData> getAllStates() {
        return state.getValidStates().stream().map(MCInterface::fromBlockState).collect(Collectors.toList());
    }

    @Override
    public IBlockData getDefaultBlockData() {
        return MCInterface.fromBlockState(state.getBaseState());
    }

    @Override
    public IBlock getBlock() {
        return MCInterface.fromBlock(state.getBlock());
    }

    @Override
    public Set<IBlockAttribute> getAttributes() {
        List<IBlockAttribute> list = new LinkedList<>();
        for(IProperty p : state.getBlock().getDefaultState().getPropertyNames()){
            list.add(MCInterface.toBlockAttribute(p));
        }
        return new HashSet<>(list);
    }

    @Override
    public <T extends Comparable<T>> IBlockData setValue(IBlockData data, IBlockAttribute<T> attr, T value) {
        return MCInterface.fromBlockState(MCInterface.toBlockState(data).withProperty(MCInterface.fromBlockAttribute(attr), value));
    }

    @Override
    public <T extends Comparable<T>> IBlockData cycleValue(IBlockData data, IBlockAttribute<T> attr) {
        return MCInterface.fromBlockState(MCInterface.toBlockState(data).cycleProperty(MCInterface.fromBlockAttribute(attr)));
    }
}
