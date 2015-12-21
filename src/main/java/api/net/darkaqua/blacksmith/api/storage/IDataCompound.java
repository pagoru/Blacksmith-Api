package net.darkaqua.blacksmith.api.storage;

public interface IDataCompound extends IDataElement{

	void removeKey(String name);

	boolean containsKey(String name);

	void setInteger(String name, int value);

	void setLong(String name, long value);

	void setFloat(String name, float value);

	void setDouble(String name, double value);

	void setString(String name, String value);

	void setIntegerArray(String name, int[] value);

	void setByte(String name, byte value);

	void setByteArray(String name, byte[] value);

	void setBoolean(String name, boolean value);

	void setDataElement(String name, IDataElement value);

	int getInteger(String name);

	long getLong(String name);

	float getFloat(String name);

	double getDouble(String name);

	String getString(String name);

	int[] getIntegerArray(String name);

	byte getByte(String name);

	byte[] getByteArray(String name);

	boolean getBoolean(String name);

	IDataElement getDataElement(String name);

	IDataList getDataList(String name);

	IDataCompound getDataCompound(String name);

	IDataCompound copy();

	Object getInternalNBTCompound();


}
