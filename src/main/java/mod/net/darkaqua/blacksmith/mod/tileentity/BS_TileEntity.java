package net.darkaqua.blacksmith.mod.tileentity;

import net.darkaqua.blacksmith.api.intermod.IInterfaceProvider;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.registry.TileEntityRegistry;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by cout970 on 14/11/2015.
 */
public class BS_TileEntity extends TileEntity implements ITickable {

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
