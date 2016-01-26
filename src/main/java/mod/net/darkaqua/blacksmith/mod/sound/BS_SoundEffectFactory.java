package net.darkaqua.blacksmith.mod.sound;

import net.darkaqua.blacksmith.api.sound.ISoundEffect;
import net.darkaqua.blacksmith.api.sound.SoundEffectFactory;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.audio.PositionedSoundRecord;

/**
 * Created by cout970 on 25/01/2016.
 */
public class BS_SoundEffectFactory extends SoundEffectFactory {

    public static void init(){
        INSTANCE = new BS_SoundEffectFactory();
    }

    private BS_SoundEffectFactory(){}

    @Override
    public ISoundEffect newSoundEffect(ResourceReference ref) {
        return MCInterface.fromSound(PositionedSoundRecord.create(MCInterface.toResourceLocation(ref)));
    }

    @Override
    public ISoundEffect newSoundEffect(ResourceReference ref, float pitch) {
        return MCInterface.fromSound(PositionedSoundRecord.create(MCInterface.toResourceLocation(ref), pitch));
    }

    @Override
    public ISoundEffect newSoundEffect(ResourceReference ref, Vect3d pos) {
        return MCInterface.fromSound(PositionedSoundRecord.create(MCInterface.toResourceLocation(ref), (float)pos.getX(), (float)pos.getY(), (float)pos.getZ()));
    }

    @Override
    public ISoundEffect newSoundEffect(ResourceReference ref, float volume, float pitch, Vect3d pos) {
        return MCInterface.fromSound(new PositionedSoundRecord(MCInterface.toResourceLocation(ref), volume, pitch, (float)pos.getX(), (float)pos.getY(), (float)pos.getZ()));
    }
}
