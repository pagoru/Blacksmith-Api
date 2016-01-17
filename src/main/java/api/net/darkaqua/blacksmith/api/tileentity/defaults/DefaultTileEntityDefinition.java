package net.darkaqua.blacksmith.api.tileentity.defaults;

import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Vect3i;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.world.IWorld;

/**
 * Created by cout970 on 21/12/2015.
 */
public class DefaultTileEntityDefinition implements ITileEntityDefinition {

    protected ITileEntity parent;

    @Override
    public void update() {
    }

    @Override
    public ITileEntity getParent() {
        return parent;
    }

    @Override
    public void bindParent(ITileEntity parent) {
        this.parent = parent;
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onDelete() {
    }

    @Override
    public void loadData(IDataCompound tag) {
    }

    @Override
    public void saveData(IDataCompound tag) {
    }

    @Override
    public IDataCompound getUpdateData(){
        return null;
    }

    @Override
    public void onUpdateDataArrives(IDataCompound data){}

    @Override
    public void onChunkUnload() {
    }

    @Override
    public boolean shouldRecreate(WorldRef ref, IBlockData oldState, IBlockData newSate) {
        return true;
    }

    @Override
    public void onBlockChange() {
    }

    @Override
    public void onClientDataArrive(int id, int data) {
    }

    @Override
    public double getRenderDistance() {
        return 64;
    }

    @Override
    public Cube getRenderBox() {
        return Cube.fullBlock().translate(parent.getWorldRef().getPosition().toVect3d());
    }

    public IWorld getWorld() {
        return getParent().getWorldRef().getWorld();
    }

    public Vect3i getPosition(){
        return getParent().getWorldRef().getPosition();
    }
}
