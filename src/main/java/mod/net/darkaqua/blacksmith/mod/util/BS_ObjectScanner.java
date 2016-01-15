package net.darkaqua.blacksmith.mod.util;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.intermod.IInterfaceProvider;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.ObjectScanner;

/**
 * Created by cout970 on 21/12/2015.
 */
public class BS_ObjectScanner extends ObjectScanner {

    private BS_ObjectScanner() {
    }

    public static void init() {
        INSTANCE = new BS_ObjectScanner();
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    protected <T> T find(Object toScan, Class<T> clazz) {
        if (clazz == null) return null;

        if (toScan instanceof IInterfaceProvider) {
            Object obj = ((IInterfaceProvider) toScan).providerInterface(clazz.getCanonicalName(), clazz);
            if (obj != null) return (T) obj;
        }

        if(toScan instanceof IBlockData){
            if(isInstance(((IBlockData) toScan).getInternalBlockState(), clazz)){
                return (T) ((IBlockData) toScan).getInternalBlockState();
            }else{
                toScan = ((IBlockData) toScan).getBlock();
            }
        }

        if (toScan instanceof IItem) {
            if (((IItem) toScan).getItemDefinition() instanceof IInterfaceProvider) {
                Object obj = ((IInterfaceProvider) ((IItem) toScan).getItemDefinition()).providerInterface(clazz.getCanonicalName(), clazz);
                if (obj != null) return (T) obj;
            }
            if (isInstance(((IItem) toScan).getItemDefinition(), clazz)) {
                return (T) ((IItem) toScan).getItemDefinition();
            } else if (isInstance(((IItem) toScan).getInternalItem(), clazz)) {
                return (T) ((IItem) toScan).getInternalItem();
            }
        } else if (toScan instanceof IBlock) {
            if (((IBlock) toScan).getBlockDefinition() instanceof IInterfaceProvider) {
                Object obj = ((IInterfaceProvider) ((IBlock) toScan).getBlockDefinition()).providerInterface(clazz.getCanonicalName(), clazz);
                if (obj != null) return (T) obj;
            }
            if (isInstance(((IBlock) toScan).getBlockDefinition(), clazz)) {
                return (T) ((IBlock) toScan).getBlockDefinition();
            } else if (isInstance(((IBlock) toScan).getInternalBlock(), clazz)) {
                return (T) ((IBlock) toScan).getInternalBlock();
            }
        } else if (toScan instanceof ITileEntity) {
            if (((ITileEntity) toScan).getTileEntityDefinition() instanceof IInterfaceProvider) {
                Object obj = ((IInterfaceProvider) ((ITileEntity) toScan).getTileEntityDefinition()).providerInterface(clazz.getCanonicalName(), clazz);
                if (obj != null) return (T) obj;
            }
            if (isInstance(((ITileEntity) toScan).getTileEntityDefinition(), clazz)) {
                return (T) ((ITileEntity) toScan).getTileEntityDefinition();
            } else if (isInstance(((ITileEntity) toScan).getInternalTileEntity(), clazz)) {
                return (T) ((ITileEntity) toScan).getInternalTileEntity();
            }
        }
        return null;
    }

    private static boolean isInstance(Object obj, Class<?> clazz) {
        return obj != null && clazz.isAssignableFrom(obj.getClass());
    }
}
