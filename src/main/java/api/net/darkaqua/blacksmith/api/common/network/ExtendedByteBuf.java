package net.darkaqua.blacksmith.api.common.network;

import io.netty.buffer.ByteBuf;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.storage.IDataCompound;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3i;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by cout970 on 09/01/2016.
 */
public interface ExtendedByteBuf {

    ByteBuf getByteBuf();

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
