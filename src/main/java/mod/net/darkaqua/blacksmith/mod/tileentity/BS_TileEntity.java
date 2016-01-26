package net.darkaqua.blacksmith.mod.tileentity;

import net.darkaqua.blacksmith.api.intermod.IInterfaceProvider;
import net.darkaqua.blacksmith.api.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.registry.TileEntityRegistry;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by cout970 on 14/11/2015.
 */
public class BS_TileEntity extends TileEntity implements ITickable, IInventory {

    protected ITileEntityDefinition def;

    public void setDefinition(ITileEntityDefinition definition) {
        this.def = definition;
        def.bindParent(MCInterface.fromTileEntity(this));
    }

    public ITileEntityDefinition getTileEntityDefinition() {
        return def;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        Class<? extends ITileEntityDefinition> clazz =
                TileEntityRegistry.INSTANCE.getDefinitionClass(compound.getString("def_id"));
        boolean error = false;

        if (clazz != null) {
            try {
                def = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                error = true;
            }
        } else {
            error = true;
        }
        if (error) {
            invalidate();
        } else {
            def.bindParent(MCInterface.fromTileEntity(this));
            NBTTagCompound cmp = (NBTTagCompound) compound.getTag("def_tag");
            def.loadData(MCInterface.fromNBTCompound(cmp));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setString("def_id", TileEntityRegistry.INSTANCE.getDefinitionID(def));
        NBTTagCompound cmp = new NBTTagCompound();
        def.saveData(MCInterface.fromNBTCompound(cmp));
        compound.setTag("def_tag", cmp);
    }

    @Override
    public Packet getDescriptionPacket() {
        return new S35PacketUpdateTileEntity(getPos(), 0, MCInterface.toNBTCompound(def.getUpdateData()));
    }

    @Override
    public void onDataPacket(net.minecraft.network.NetworkManager net, net.minecraft.network.play.server.S35PacketUpdateTileEntity pkt) {
        def.onUpdateDataArrives(MCInterface.fromNBTCompound(pkt.getNbtCompound()));
    }

    @Override
    public void invalidate() {
        super.invalidate();
        if (def != null) {
            def.onDelete();
        }
    }

    @Override
    public double getMaxRenderDistanceSquared() {
        double dist = def.getRenderDistance();
        return dist * dist;
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        def.onClientDataArrive(id, type);
        return false;
    }

    @Override
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
        def.onBlockChange();
    }

    @Override
    public void onChunkUnload() {
        def.onChunkUnload();
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return def.shouldRecreate(new WorldRef(MCInterface.fromWorld(world), MCInterface.fromBlockPos(pos)), MCInterface.fromIBlockState(oldState), MCInterface.fromIBlockState(newSate));
    }

    @Override
    public net.minecraft.util.AxisAlignedBB getRenderBoundingBox() {
        return MCInterface.toAxisAlignedBB(def.getRenderBox());
    }

    @Override
    public void update() {
        def.update();
    }

    @Override
    public void onLoad() {
        def.onLoad();
    }

    @Override
    public int getSizeInventory() {
        if (def instanceof IInterfaceProvider && ((IInterfaceProvider) def).hasInterface(IInventoryHandler.IDENTIFIER, null)) {
            IInventoryHandler inv = ((IInterfaceProvider) def).getInterface(IInventoryHandler.IDENTIFIER, null);
            return inv.getSlots();
        }
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (def instanceof IInterfaceProvider && ((IInterfaceProvider) def).hasInterface(IInventoryHandler.IDENTIFIER, null)) {
            IInventoryHandler inv = ((IInterfaceProvider) def).getInterface(IInventoryHandler.IDENTIFIER, null);
            return MCInterface.toItemStack(inv.getStackInSlot(index));
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (def instanceof IInterfaceProvider && ((IInterfaceProvider) def).hasInterface(IInventoryHandler.IDENTIFIER, null)) {
            IInventoryHandler inv = ((IInterfaceProvider) def).getInterface(IInventoryHandler.IDENTIFIER, null);
            IItemStack ret = inv.extractItemStack(index, count, false);
            return MCInterface.toItemStack(ret);
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (def instanceof IInterfaceProvider && ((IInterfaceProvider) def).hasInterface(IInventoryHandler.IDENTIFIER, null)) {
            IInventoryHandler inv = ((IInterfaceProvider) def).getInterface(IInventoryHandler.IDENTIFIER, null);
            setStackInSlot(inv, index, MCInterface.fromItemStack(stack));
        }
    }

    private void setStackInSlot(IInventoryHandler inv, int index, IItemStack iItemStack) {
        inv.extractItemStack(index, Integer.MAX_VALUE, false);
        inv.insertItemStack(index, iItemStack, false);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        if (def instanceof IInterfaceProvider && ((IInterfaceProvider) def).hasInterface(IInventoryHandler.IDENTIFIER, null)) {
            IInventoryHandler inv = ((IInterfaceProvider) def).getInterface(IInventoryHandler.IDENTIFIER, null);
            for (int i = 0; i < inv.getSlots(); i++) {
                setStackInSlot(inv, i, null);
            }
        }
    }

    @Override
    public String getCommandSenderName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (def instanceof IInterfaceProvider){
            ((IInterfaceProvider) def).hasInterface(MCInterface.fromCapability(capability), MCInterface.fromEnumFacing(facing));
        }
        return false;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (def instanceof IInterfaceProvider){
            ((IInterfaceProvider) def).getInterface(MCInterface.fromCapability(capability), MCInterface.fromEnumFacing(facing));
        }
        return null;
    }
}
