package net.darkaqua.blacksmith.api.intermod;

/**
 * Created by cout970 on 29/12/2015.
 */
public interface IInterfaceProvider {

    Object providerInterface(String className, Class<?> interfaceClass);
}