package net.darkaqua.blacksmith.mod.network.channel;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.network.ExtendedByteBuf;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.util.Vect3i;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.network.PacketBuffer;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by cout970 on 09/01/2016.
 */
public class BS_ExtendedByteBuf implements ExtendedByteBuf {

    private PacketBuffer buff;

    public BS_ExtendedByteBuf(PacketBuffer buff) {
        this.buff = buff;
    }

    @Override
    public void writeByteArray(byte[] array) {
        buff.writeByteArray(array);
    }

    @Override
    public byte[] readByteArray() {
        return buff.readByteArray();
    }

    @Override
    public Vect3i readVect3i() {
        return MCInterface.fromBlockPos(buff.readBlockPos());
    }

    @Override
    public void writeVect3i(Vect3i vec) {
        buff.writeBlockPos(MCInterface.toBlockPos(vec));
    }

    @Override
    public <T extends Enum<T>> T readEnumValue(Class<T> enumClass) {
        return buff.readEnumValue(enumClass);
    }

    @Override
    public void writeEnumValue(Enum<?> value) {
        buff.writeEnumValue(value);
    }

    @Override
    public void writeUUID(UUID uuid) {
        buff.writeUuid(uuid);
    }

    @Override
    public UUID readUUID() {
        return buff.readUuid();
    }

    @Override
    public void writeDataCompound(IDataCompound data) {
        buff.writeNBTTagCompoundToBuffer(MCInterface.toNBTCompound(data));
    }

    @Override
    public IDataCompound readDataCompound() throws IOException {
        return MCInterface.fromNBTCompound(buff.readNBTTagCompoundFromBuffer());
    }

    @Override
    public void writeItemStack(IItemStack stack) {
        buff.writeItemStackToBuffer(MCInterface.toItemStack(stack));
    }

    @Override
    public IItemStack readItemStack() throws IOException {
        return MCInterface.fromItemStack(buff.readItemStackFromBuffer());
    }
}
