package net.darkaqua.blacksmith.mod.client.sound;

import net.darkaqua.blacksmith.api.client.sound.ISoundEffect;
import net.darkaqua.blacksmith.api.client.sound.SoundEffectFactory;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.client.audio.ISound;

/**
 * Created by cout970 on 25/01/2016.
 */
public class BS_SoundEffectFactory extends SoundEffectFactory {

    public static void init() {
        INSTANCE = new BS_SoundEffectFactory();
    }

    private BS_SoundEffectFactory() {
    }

    @Override
    public ISoundEffect newSoundEffect(ResourceReference ref, float volume, float pitch, boolean repeat,
                                       int repeatDelay, ISoundEffect.AttenuationType at, Vect3d pos) {

        return MCInterface.fromSound(new SoundEffect(MCInterface.toResourceLocation(ref), volume, pitch, repeat,
                repeatDelay, ISound.AttenuationType.values()[at.ordinal()], (float) pos.getX(),
                (float) pos.getY(), (float) pos.getZ()));
    }
}
