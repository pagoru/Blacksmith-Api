package net.darkaqua.blacksmith.api.block.variants.defaults;

import net.darkaqua.blacksmith.api.block.variants.IBlockAttributeValue;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockAttributeValueInteger implements IBlockAttributeValue {

    public final Integer value;

    public BlockAttributeValueInteger(Integer value) {
        this.value = value;
    }

    public static BlockAttributeValueInteger[] generateValues(int min, int max){
        max++;
        if(min >= max){
            throw new IllegalArgumentException("Min cannot be bigger than max");
        }

        BlockAttributeValueInteger[] array = new BlockAttributeValueInteger[max-min];
        for(int i = min; i < max; i++){
            array[i] = new BlockAttributeValueInteger(i);
        }
        return array;
    }

    @Override
    public String getName() {
        return ""+value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public int compareTo(IBlockAttributeValue o) {
        return o instanceof BlockAttributeValueInteger ? ((BlockAttributeValueInteger) o).value.compareTo(value) : -1;
    }

    @Override
    public String toString(){
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockAttributeValueInteger)) return false;

        BlockAttributeValueInteger that = (BlockAttributeValueInteger) o;

        return !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
