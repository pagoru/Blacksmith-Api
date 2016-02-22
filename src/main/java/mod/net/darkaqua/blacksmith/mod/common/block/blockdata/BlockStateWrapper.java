package net.darkaqua.blacksmith.mod.common.block.blockdata;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttribute;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockDataGenerator;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.block.state.BlockState;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockStateWrapper implements IBlockDataGenerator {

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
    public IBlockData getDefaultData() {
        return MCInterface.fromBlockState(state.getBaseState());
    }

    @Override
    public IBlock getBlock() {
        return MCInterface.fromBlock(state.getBlock());
    }

    @Override
    public Set<IBlockAttribute> getAttributes() {
        return state.getProperties().stream().map(MCInterface::toBlockAttribute).collect(Collectors.toSet());
    }
}
