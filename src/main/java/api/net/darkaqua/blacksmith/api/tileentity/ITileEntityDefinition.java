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

    default void onLoad(){}

    default void onDelete(){}

    default void update(){}

    default void loadData(IDataCompound tag){};

    default void saveData(IDataCompound tag){}

    default IDataCompound getUpdateData(){
        return null;
    }

    default void onUpdateDataArrives(IDataCompound data){}

    default void onChunkUnload(){}

    default boolean shouldRecreate(WorldRef ref, IBlockData oldState, IBlockData newSate){
        return true;
    }

    default void onBlockChange(){}

    default void onClientDataArrive(int id, int data){}

    default double getRenderDistance(){
        return 64;
    }

    default Cube getRenderBox(){
        return Cube.fullBlock().translate(getParent().getWorldRef().getPosition().toVect3d());
    }
}
