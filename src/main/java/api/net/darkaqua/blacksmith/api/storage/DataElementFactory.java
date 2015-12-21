package net.darkaqua.blacksmith.api.storage;

/**
 * Created by cout970 on 21/12/2015.
 */
public abstract class DataElementFactory {

    protected static DataElementFactory INSTANCE;

    public static IDataCompound createDataCompound(){
        return INSTANCE.newDataCompound();
    }

    public static IDataList createDataList(){
        return INSTANCE.newDataList();
    }

    protected abstract IDataCompound newDataCompound();
    protected abstract IDataList newDataList();
}
