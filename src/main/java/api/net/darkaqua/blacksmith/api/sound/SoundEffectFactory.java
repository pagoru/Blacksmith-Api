package net.darkaqua.blacksmith.api.sound;

import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;

/**
 * Created by cout970 on 25/01/2016.
 */
public abstract class SoundEffectFactory {

    protected static SoundEffectFactory INSTANCE;
    public static final ResourceReference BUTTON_PRESS = new ResourceReference("minecraft", "gui.button.press");

    public static ISoundEffect createSoundEffect(ResourceReference soundResource) {
        return INSTANCE.newSoundEffect(soundResource, 1F, 1f, false, 0, ISoundEffect.AttenuationType.NONE, Vect3d.nullVector());
    }

    public static ISoundEffect createSoundEffect(ResourceReference ref, Vect3d pos, float volume, float pitch) {
        return INSTANCE.newSoundEffect(ref, volume, pitch, false, 0, ISoundEffect.AttenuationType.NONE, pos);
    }

    public static ISoundEffect createSoundEffect(ResourceReference ref, Vect3d pos) {
        return INSTANCE.newSoundEffect(ref, 1F, 1f, false, 0, ISoundEffect.AttenuationType.NONE, pos);
    }

    public static ISoundEffect createSoundEffect(ResourceReference ref, Vect3d pos, float volume, float pitch, boolean repeat, int repeatDelay, ISoundEffect.AttenuationType attenuationType) {
        return INSTANCE.newSoundEffect(ref, volume, pitch, repeat, repeatDelay, attenuationType, pos);
    }


    public abstract ISoundEffect newSoundEffect(ResourceReference ref, float volume, float pitch, boolean repeat, int repeatDelay, ISoundEffect.AttenuationType attenuationType, Vect3d pos);
}
