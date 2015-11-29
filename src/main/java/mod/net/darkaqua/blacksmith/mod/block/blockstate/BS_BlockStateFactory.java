package net.darkaqua.blacksmith.mod.block.blockstate;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.blockstate.BlockStateFactory;
import net.darkaqua.blacksmith.api.block.blockstate.IIBlockState;
import net.darkaqua.blacksmith.api.block.blockstate.IIProperty;

import java.util.Map;

/**
 * Created by cout970 on 28/11/2015.
 */
public class BS_BlockStateFactory extends BlockStateFactory{

    public static void init(){
        INSTANCE = new BS_BlockStateFactory();
    }

    private BS_BlockStateFactory(){}

    @Override
    protected IIBlockState newBlockState(IBlock block, IIProperty[] properties) {
        return null;
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
