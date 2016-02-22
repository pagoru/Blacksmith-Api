package net.darkaqua.blacksmith.mod.common.util;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.intermod.IInterfaceIdentifier;
import net.darkaqua.blacksmith.api.common.intermod.IInterfaceProvider;
import net.darkaqua.blacksmith.api.common.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.item.IItem;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.ObjectScanner;
import net.darkaqua.blacksmith.mod.common.inventory.SidedInventoryWrapper;
import net.darkaqua.blacksmith.mod.common.inventory.SimpleInventoryWrapper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraftforge.common.capabilities.Capability;
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
    @SuppressWarnings("unchecked")
    protected <T> T findInterface(Object toScan, IInterfaceIdentifier<T> id, Direction dir) {
        IInterfaceProvider provider = find(toScan, IInterfaceProvider.class);
        if (provider != null){
            if (provider.hasInterface(id, dir)){
                return provider.getInterface(id, dir);
            }
        }
        ICapabilityProvider prov = find(toScan, ICapabilityProvider.class);
        if(prov != null){
            if(prov.hasCapability(MCInterface.toCapability(id), MCInterface.toEnumFacing(dir))){
                return prov.getCapability((Capability<T>) MCInterface.toCapability(id), MCInterface.toEnumFacing(dir));
            }
        }

        //adding forge inventories
        if (id == IInventoryHandler.IDENTIFIER){
            IInventory inv = find(toScan, IInventory.class);
            if (inv != null){
                if (inv instanceof ISidedInventory){
                    return (T) new SidedInventoryWrapper(MCInterface.toEnumFacing(dir), (ISidedInventory) inv);
                }else{
                    return (T) new SimpleInventoryWrapper(inv);
                }
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
