package net.darkaqua.blacksmith.mod.util;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.ObjectScanner;

/**
 * Created by cout970 on 21/12/2015.
 */
public class BS_ObjectScanner extends ObjectScanner {

    private BS_ObjectScanner(){}

    public static void init(){
        INSTANCE = new BS_ObjectScanner();
    }

    @Override
    protected <T> T find(Object toScan, Class<T> clazz) {
        if (clazz == null) return null;

        if(toScan instanceof IBlockVariant){
            if(isInstance(((IBlockVariant) toScan).getInternalBlockState(), clazz)){
                return (T) ((IBlockVariant) toScan).getInternalBlockState();
            }else{
                toScan = ((IBlockVariant) toScan).getBlock();
            }
        }

        if (toScan instanceof IItem){
            if(isInstance(((IItem) toScan).getItemDefinition(), clazz)){
                return (T) ((IItem) toScan).getItemDefinition();
            }else if(isInstance(((IItem) toScan).getInternalItem(), clazz)){
                return (T) ((IItem) toScan).getInternalItem();
            }
        }else if(toScan instanceof IBlock){
            if(isInstance(((IBlock) toScan).getBlockDefinition(), clazz)){
                return (T) ((IBlock) toScan).getBlockDefinition();
            }else if(isInstance(((IBlock) toScan).getInternalBlock(), clazz)){
                return (T) ((IBlock) toScan).getInternalBlock();
            }
        }else if(toScan instanceof ITileEntity){
            if(isInstance(((ITileEntity) toScan).getTileEntityDefinition(), clazz)){
                return (T) ((ITileEntity) toScan).getTileEntityDefinition();
            }else if(isInstance(((ITileEntity) toScan).getInternalTileEntity(), clazz)){
                return (T) ((ITileEntity) toScan).getInternalTileEntity();
            }
        }

        return null;
    }

    private static boolean isInstance(Object obj, Class<?> clazz){
        return obj != null && clazz.isAssignableFrom(obj.getClass());
    }
}
