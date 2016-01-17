package net.darkaqua.blacksmith.api.tileentity;

import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.util.annotations.Implementable;

/**
 * Created by cout970 on 13/11/2015.
 */
@Implementable
public interface ITileEntityDefinition {

    ITileEntity getParent();

    void bindParent(ITileEntity parent);

    void onLoad();

    void onDelete();

    void update();

    void loadData(IDataCompound tag);

    void saveData(IDataCompound tag);

    IDataCompound getUpdateData();

    void onUpdateDataArrives(IDataCompound data);

    void onChunkUnload();

    boolean shouldRecreate(WorldRef ref, IBlockData oldState, IBlockData newSate);

    void onBlockChange();

    void onClientDataArrive(int id, int data);

    double getRenderDistance();

    Cube getRenderBox();
}
