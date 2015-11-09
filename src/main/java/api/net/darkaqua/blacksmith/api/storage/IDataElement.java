package net.darkaqua.blacksmith.api.storage;

public interface IDataElement {

	public byte getID();
	
	public IDataElement copy();
}
