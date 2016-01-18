package net.darkaqua.blacksmith.mod.util;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.intermod.IInterfaceIdentifier;
import net.darkaqua.blacksmith.api.intermod.IInterfaceProvider;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.ObjectScanner;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * Created by cout970 on 21/12/2015.
 */
public class BS_ObjectScanner extends ObjectScanner {

    private BS_ObjectScanner() {
    }

    public static void init() {
        INSTANCE = new BS_ObjectScanner();
    }

    @Override
    protected Object findInterface(Object toScan, IInterfaceIdentifier id, Direction dir) {
        IInterfaceProvider provider = find(toScan, IInterfaceProvider.class);
        if (provider != null){
            if (provider.hasInterface(id, dir)){
                return provider.getInterface(id, dir);
            }
        }
        ICapabilityProvider prov = find(toScan, ICapabilityProvider.class);
        if(prov != null){
            if(prov.hasCapability(MCInterface.toCapability(id), MCInterface.toEnumFacing(dir))){
                return prov.getCapability(MCInterface.toCapability(id), MCInterface.toEnumFacing(dir));
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T find(Object toScan, Class<T> clazz) {
        if (clazz == null) return null;

        if(toScan instanceof IItemStack){
            if(isInstance(((IItemStack) toScan).getInternalItemStack(), clazz)){
                return (T) ((IItemStack) toScan).getInternalItemStack();
            }else{
                return find(((IItemStack) toScan).getInternalItemStack(), clazz);
            }
        }else if(toScan instanceof IBlockData){
            if(isInstance(((IBlockData) toScan).getInternalBlockState(), clazz)){
                return (T) ((IBlockData) toScan).getInternalBlockState();
            }else{
                return find(((IBlockData) toScan).getInternalBlockState(), clazz);
            }
        }else if (toScan instanceof IItem) {
            if (isInstance(((IItem) toScan).getItemDefinition(), clazz)) {
                return (T) ((IItem) toScan).getItemDefinition();
            } else if (isInstance(((IItem) toScan).getInternalItem(), clazz)) {
                return (T) ((IItem) toScan).getInternalItem();
            }
        } else if (toScan instanceof IBlock) {
            if (isInstance(((IBlock) toScan).getBlockDefinition(), clazz)) {
                return (T) ((IBlock) toScan).getBlockDefinition();
            } else if (isInstance(((IBlock) toScan).getInternalBlock(), clazz)) {
                return (T) ((IBlock) toScan).getInternalBlock();
            }
        } else if (toScan instanceof ITileEntity) {
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
