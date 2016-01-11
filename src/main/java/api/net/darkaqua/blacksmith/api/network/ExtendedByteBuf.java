package net.darkaqua.blacksmith.api.network;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.Vect3i;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by cout970 on 09/01/2016.
 */
public interface ExtendedByteBuf {

    void writeByteArray(byte[] array);

    byte[] readByteArray();

    Vect3i readVect3i();

    void writeVect3i(Vect3i vec);

    <T extends Enum<T>> T readEnumValue(Class<T> enumClass);

    void writeEnumValue(Enum<?> value);

    void writeUUID(UUID uuid);

    UUID readUUID();

    void writeDataCompound(IDataCompound data);

    IDataCompound readDataCompound() throws IOException;

    void writeItemStack(IItemStack stack);

    IItemStack readItemStack() throws IOException;
}
