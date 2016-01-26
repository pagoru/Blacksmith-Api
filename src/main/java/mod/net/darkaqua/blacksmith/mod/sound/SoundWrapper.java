package net.darkaqua.blacksmith.mod.sound;

import net.darkaqua.blacksmith.api.sound.ISoundEffect;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.audio.ISound;

/**
 * Created by cout970 on 25/01/2016.
 */
public class SoundWrapper implements ISoundEffect {

    private ISound sound;

    public SoundWrapper(ISound sound) {
        this.sound = sound;
    }

    public ISound getSound() {
        return sound;
    }

    @Override
    public ResourceReference getSoundReference() {
        return MCInterface.fromResourceLocation(sound.getSoundLocation());
    }

    @Override
    public boolean canRepeat() {
        return sound.canRepeat();
    }

    @Override
    public int getRepeatDelay() {
        return sound.getRepeatDelay();
    }

    @Override
    public float getVolume() {
        return sound.getVolume();
    }

    @Override
    public float getPitch() {
        return sound.getPitch();
    }

    @Override
    public Vect3d getPosition() {
        return new Vect3d(sound.getXPosF(), sound.getYPosF(), sound.getZPosF());
    }

    @Override
    public AttenuationType getAttenuationType() {
        return AttenuationType.values()[sound.getAttenuationType().ordinal()];
    }
}
