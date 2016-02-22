package net.darkaqua.blacksmith.api.common.intermod;

import net.darkaqua.blacksmith.api.common.util.Direction;

/**
 * Created by cout970 on 29/12/2015.
 */
public interface IInterfaceProvider {

    boolean hasInterface(IInterfaceIdentifier<?> identifier, Direction side);

    <T> T getInterface(IInterfaceIdentifier<T> identifier, Direction side);
}