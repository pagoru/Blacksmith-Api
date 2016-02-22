package net.darkaqua.blacksmith.api.common.block.blockdata.defaults;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttributeValue;

/**
 * Created by cout970 on 15/01/2016.
 */
public class BlockAttributeValueInteger implements IBlockAttributeValue {

    public static final BlockAttributeValueInteger[] VALUES;
    public final Integer value;

    static{
        VALUES = new BlockAttributeValueInteger[16];
        for(int i = 0; i < 16; i++){
            VALUES[i] = new BlockAttributeValueInteger(i);
        }
    }

    private BlockAttributeValueInteger(Integer value) {
        this.value = value;
    }

    public static BlockAttributeValueInteger getValueByInt(int i){
        return VALUES[i % VALUES.length];
    }

    @Override
    public String getValueName() {
        return ""+value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public IBlockAttributeValue getCanonicalValue() {
        return VALUES[value];
    }

    @Override
    public int compareTo(IBlockAttributeValue o) {
        return o instanceof BlockAttributeValueInteger ? ((BlockAttributeValueInteger) o).value.compareTo(value) : -1;
    }

    @Override
    public String toString(){
        return getValueName();
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
