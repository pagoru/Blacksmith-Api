package net.darkaqua.blacksmith.mod.block.blockstate;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.block.BlockVariantCreatorFactory;
import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockVariantCreator;
import net.darkaqua.blacksmith.api.block.IIProperty;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;

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
    protected <T extends Comparable<T>> IIProperty<T> newIProperty(String name, Map<String, T> val, Class<T> valuesClass) {
        return MCInterface.fromIProperty(new BS_Property<>(name, val, valuesClass));
    }

    @Override
    protected IIProperty<Integer> newPropertyInteger(String name, int min, int max) {
        return MCInterface.fromIProperty(PropertyInteger.create(name, min, max));
    }

    @Override
    protected <T extends Enum<T>> IIProperty<T> newPropertyEnum(String name, Class<T> clazz) {
        return MCInterface.fromIProperty(new BS_PropertyEnum<T>(name, clazz, Lists.newArrayList(clazz.getEnumConstants())));
    }

    @Override
    protected IIProperty<Boolean> newPropertyBoolean(String name) {
        return MCInterface.fromIProperty(PropertyBool.create(name));
    }
}
