package net.darkaqua.blacksmith.api.intermod;

import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.Direction;

/**
 * Created by cout970 on 16/01/2016.
 */
public interface IInterfaceIdentifier<T> {

    String getName();

    Class<T> getInterfaceClass();

    Object getDefaultInstance();

    IStorageHandler getStorageHandler();

    interface IStorageHandler<T> {
        IDataCompound saveData(IInterfaceIdentifier<T> identifier, T instance, Direction dir);

        void loadData(IInterfaceIdentifier<T> identifier, T instance, Direction dir, IDataCompound data);
    }
}
