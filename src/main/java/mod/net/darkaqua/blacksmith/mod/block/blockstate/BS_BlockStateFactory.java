package net.darkaqua.blacksmith.mod.block.blockstate;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IPropertyFactory;
import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.block.IIProperty;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;

import java.util.Map;

/**
 * Created by cout970 on 28/11/2015.
 */
public class BS_BlockStateFactory extends IPropertyFactory {

    public static void init(){
        INSTANCE = new BS_BlockStateFactory();
    }

    private BS_BlockStateFactory(){}

    @Override
    protected IBlockVariant newBlockState(IBlock block, IIProperty[] properties) {
        IProperty[] prop = new IProperty[properties.length];
        for(int i = 0; i < properties.length; i++){
            prop[i] = MCInterface.toIProperty(properties[i]);
        }
        IBlockState state = new BlockState(MCInterface.toBlock(block), prop).getBaseState();
        return MCInterface.fromIBlockVariant(state);
    }

    @Override
    protected <T> IIProperty newIProperty(String name, Map<String, Comparable<T>> values, Class<T> valuesClass) {
        return null;
    }

    @Override
    protected IIProperty newPropertyInteger(String name, int min, int max) {
        return null;
    }

    @Override
    protected IIProperty newPropertyEnum(String name, Class<? extends Enum> clazz) {
        return null;
    }

    @Override
    protected IIProperty newPropertyBoolean(String name) {
        return null;
    }

    @Override
    protected IIProperty newPropertyDirection(String name) {
        return null;
    }
}
