package net.darkaqua.blacksmith.api.sound;

import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;

/**
 * Created by cout970 on 25/01/2016.
 */
public abstract class SoundEffectFactory {

    protected static SoundEffectFactory INSTANCE;

    public ISoundEffect createSoundEffect(ResourceReference ref) {
        return INSTANCE.createSoundEffect(ref);
    }

    public ISoundEffect createSoundEffect(ResourceReference ref, float pitch) {
        return INSTANCE.createSoundEffect(ref, pitch);
    }

    public ISoundEffect createSoundEffect(ResourceReference ref, float volume, float pitch, Vect3d pos) {
        return INSTANCE.createSoundEffect(ref, volume, pitch, pos);
    }

    public ISoundEffect createSoundEffect(ResourceReference ref, Vect3d pos) {
        return INSTANCE.createSoundEffect(ref, pos);
    }

    public abstract ISoundEffect newSoundEffect(ResourceReference ref);
    public abstract ISoundEffect newSoundEffect(ResourceReference ref, float pitch);
    public abstract ISoundEffect newSoundEffect(ResourceReference ref, Vect3d pos);
    public abstract ISoundEffect newSoundEffect(ResourceReference ref, float volume, float pitch, Vect3d pos);
}
