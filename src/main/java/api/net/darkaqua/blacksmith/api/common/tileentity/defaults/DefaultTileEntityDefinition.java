package net.darkaqua.blacksmith.api.common.tileentity.defaults;

import net.darkaqua.blacksmith.api.common.storage.IDataCompound;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3i;
import net.darkaqua.blacksmith.api.common.world.IWorld;

/**
 * Created by cout970 on 21/12/2015.
 */
public class DefaultTileEntityDefinition implements ITileEntityDefinition {

    protected ITileEntity parent;

    @Override
    public ITileEntity getParent() {
        return parent;
    }

    @Override
    public void bindParent(ITileEntity parent) {
        this.parent = parent;
    }

    @Override
    public void loadData(IDataCompound data){}

    @Override
    public void saveData(IDataCompound data){}

    public IWorld getWorld() {
        return getParent().getWorldRef().getWorld();
    }

    public Vect3i getPosition(){
        return getParent().getWorldRef().getPosition();
    }
}
