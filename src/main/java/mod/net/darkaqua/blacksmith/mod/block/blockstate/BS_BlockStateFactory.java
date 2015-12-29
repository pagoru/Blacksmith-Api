package net.darkaqua.blacksmith.mod.block.blockstate;

import net.darkaqua.blacksmith.api.block.BlockVariantCreatorFactory;
import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockVariantCreator;
import net.darkaqua.blacksmith.api.block.IIProperty;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.BlockState;
import net.minecraft.util.IStringSerializable;

import java.util.Map;

/**
 * Created by cout970 on 28/11/2015.
 */
public class BS_BlockStateFactory extends BlockVariantCreatorFactory {

    public static void init(){
        INSTANCE = new BS_BlockStateFactory();
    }

    private BS_BlockStateFactory(){}

    @Override
    protected IBlockVariantCreator newBlockVariantCreator(IBlock block, IIProperty[] properties) {
        IProperty[] prop = new IProperty[properties.length];
        for(int i = 0; i < properties.length; i++){
            prop[i] = MCInterface.toIProperty(properties[i]);
        }
        return MCInterface.fromBlockStateCreator(new BlockState(MCInterface.toBlock(block), prop));
    }

    @Override
    protected <T extends Comparable<T>> IIProperty newIProperty(String name, Map<String, T> val, Class<T> valuesClass) {
        return MCInterface.fromIProperty(new BS_Property<>(name, val, valuesClass));
    }

    @Override
    protected IIProperty newPropertyInteger(String name, int min, int max) {
        return MCInterface.fromIProperty(PropertyInteger.create(name, min, max));
    }

    @Override
    protected <T extends Enum<T> & IStringSerializable> IIProperty newPropertyEnum(String name, Class<T> clazz) {
        return MCInterface.fromIProperty(PropertyEnum.create(name, clazz));
    }

    @Override
    protected IIProperty newPropertyBoolean(String name) {
        return MCInterface.fromIProperty(PropertyBool.create(name));
    }

    @Override
    protected IIProperty newPropertyDirection(String name) {
        return MCInterface.fromIProperty(PropertyDirection.create(name));
    }
}
