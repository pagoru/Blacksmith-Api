package net.darkaqua.blacksmith.api.common.intermod;

import net.darkaqua.blacksmith.api.common.storage.IDataCompound;
import net.darkaqua.blacksmith.api.common.util.Direction;

/**
 * Created by cout970 on 16/01/2016.
 */
public interface IInterfaceIdentifier<T> {

    String getName();

    Class<T> getInterfaceClass();

    T getDefaultInstance();

    IStorageHandler<T> getStorageHandler();

    boolean matches(IInterfaceIdentifier<?> inter);

    interface IStorageHandler<T> {

        IDataCompound saveData(IInterfaceIdentifier<T> identifier, T instance, Direction dir);

        void loadData(IInterfaceIdentifier<T> identifier, T instance, Direction dir, IDataCompound data);
    }
}
