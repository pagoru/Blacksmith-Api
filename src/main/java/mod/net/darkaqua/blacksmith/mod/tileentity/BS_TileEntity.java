package net.darkaqua.blacksmith.mod.tileentity;

import net.darkaqua.blacksmith.api.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.inventory.IInventoryProvider;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.inventory.InventoryUtils;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.registry.TileEntityRegistry;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

/**
 * Created by cout970 on 14/11/2015.
 */
public class BS_TileEntity extends TileEntity implements IUpdatePlayerListBox, ISidedInventory{

    protected ITileEntityDefinition def;

    public void setDefinition(ITileEntityDefinition definition) {
        this.def = definition;
        def.onLoad(MCInterface.fromTileEntity(this));
    }

    public ITileEntityDefinition getTileEntityDefinition(){
        return def;
    }

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
            def.onLoad(MCInterface.fromTileEntity(this));
            NBTTagCompound cmp = (NBTTagCompound) compound.getTag("def_tag");
            def.loadData(MCInterface.fromNBTCompound(cmp));
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setString("def_id", TileEntityRegistry.INSTANCE.getDefinitionID(def));
        NBTTagCompound cmp = new NBTTagCompound();
        def.saveData(MCInterface.fromNBTCompound(cmp));
        compound.setTag("def_tag", cmp);
    }

    public Packet getDescriptionPacket() {
        return MCInterface.fromDescriptionPacket(def.getDescriptionPacket());
    }

    public void onDataPacket(net.minecraft.network.NetworkManager net, net.minecraft.network.play.server.S35PacketUpdateTileEntity pkt) {
        def.onDescriptionPacketArrives(MCInterface.toDescriptionPacket(pkt));
    }

    public double getMaxRenderDistanceSquared() {
        double dist = def.getRenderDistance();
        return dist * dist;
    }

    public boolean receiveClientEvent(int id, int type) {
        def.onClientDataArrive(id, type);
        return false;
    }

    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
        def.onBlockChange();
    }

    public void onChunkUnload() {
        def.onChunkUnload();
    }

    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return def.shouldRecreate(new WorldRef(MCInterface.fromWorld(world), MCInterface.fromBlockPos(pos)), MCInterface.fromIBlockState(oldState), MCInterface.fromIBlockState(newSate));
    }

    public net.minecraft.util.AxisAlignedBB getRenderBoundingBox(){
        return MCInterface.toAxisAlignedBB(def.getRenderBox());
    }

    @Override
    public void update() {
        def.update();
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        if(def instanceof IInventoryProvider){
            IInventoryHandler inv = ((IInventoryProvider) def).getInventory();
            int slots = inv.getSlots(MCInterface.fromEnumFacing(side));
            int[] res = new int[slots];
            for(int i = 0; i < slots; i++){
                res[i] = i;
            }
            return res;
        }
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        if(def instanceof IInventoryProvider) {
            IInventoryHandler inv = ((IInventoryProvider) def).getInventory();
            IItemStack stack = MCInterface.fromItemStack(itemStackIn);
            IItemStack stack2 = inv.insertItemStack(MCInterface.fromEnumFacing(direction), index, stack, true);
            return !InventoryUtils.areExactlyEqual(stack, stack2);
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        if(def instanceof IInventoryProvider) {
            IInventoryHandler inv = ((IInventoryProvider) def).getInventory();
            return inv.extractItemStack(MCInterface.fromEnumFacing(direction), index, itemStackIn.stackSize, true) != null;
        }
        return false;
    }

    @Override
    public int getSizeInventory() {
        if(def instanceof IInventoryProvider) {
            IInventoryHandler inv = ((IInventoryProvider) def).getInventory();
            return inv.getSlots(null);
        }
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if(def instanceof IInventoryProvider) {
            IInventoryHandler inv = ((IInventoryProvider) def).getInventory();
            return MCInterface.toItemStack(inv.getStackInSlot(null, index));
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if(def instanceof IInventoryProvider) {
            IInventoryHandler inv = ((IInventoryProvider) def).getInventory();
            IItemStack ret = inv.extractItemStack(null, index, count, false);
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
        if(def instanceof IInventoryProvider) {
            IInventoryHandler inv = ((IInventoryProvider) def).getInventory();
            inv.setStackInSlot(null, index, MCInterface.fromItemStack(stack));
        }
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
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

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
        if(def instanceof IInventoryProvider) {
            IInventoryHandler inv = ((IInventoryProvider) def).getInventory();
            for (Direction d : Direction.values()) {
                for (int i = 0; i < inv.getSlots(d); i++) {
                    inv.setStackInSlot(d, i, null);
                }
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
}
