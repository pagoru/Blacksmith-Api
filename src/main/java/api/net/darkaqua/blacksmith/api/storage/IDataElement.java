package net.darkaqua.blacksmith.api.storage;

public interface IDataElement {

    byte getID();

    IDataElement copy();

    Object getInternalNBTBase();
}
