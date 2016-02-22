package net.darkaqua.blacksmith.api.common.registry;

import net.darkaqua.blacksmith.api.common.intermod.IInterfaceIdentifier;

import java.util.concurrent.Callable;

/**
 * Created by cout970 on 16/01/2016.
 */
public interface IInterModRegistry {

    <T> void registerInterface(Class<T> type, IInterfaceIdentifier.IStorageHandler storage, Callable<? extends T> factory);

}
