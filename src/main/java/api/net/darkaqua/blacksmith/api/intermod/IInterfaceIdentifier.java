package net.darkaqua.blacksmith.api.intermod;

import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.Direction;

/**
 * Created by cout970 on 16/01/2016.
 */
public interface IInterfaceIdentifier {

    String getName();

    Class<?> getInterfaceClass();

    Object getDefaultInstance();

    IStorageHandler getStorageHandler();

    interface IStorageHandler {
        IDataCompound saveData(IInterfaceIdentifier identifier, Object instance, Direction dir);

        void loadData(IInterfaceIdentifier identifier, Object instance, Direction dir, IDataCompound data);
    }
}
