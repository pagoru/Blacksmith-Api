package net.darkaqua.blacksmith.mod.tileentity;

import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.registry.TileEntityRegistry;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Created by cout970 on 14/11/2015.
 */
public class BS_TileEntity extends TileEntity implements IUpdatePlayerListBox{

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
}
