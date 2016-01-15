package net.darkaqua.blacksmith.mod.block.blockdata;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockAttribute;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockDataGenerator;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.state.BlockState;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockDataGenerator implements IBlockDataGenerator {

    private BlockState state;

    public BlockDataGenerator(BlockState state) {
        this.state = state;
    }

    public BlockState getBlockState() {
        return state;
    }

    @Override
    public List<IBlockData> getAllStates() {
        return state.getValidStates().stream().map(MCInterface::fromIBlockState).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public IBlockData getDefaultData() {
        return MCInterface.fromIBlockState(state.getBaseState());
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
