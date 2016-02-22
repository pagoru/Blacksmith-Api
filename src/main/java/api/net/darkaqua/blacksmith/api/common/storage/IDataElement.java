package net.darkaqua.blacksmith.api.common.storage;

public interface IDataElement {

    byte getID();

    IDataElement copy();

    Object getInternalNBTBase();
}
