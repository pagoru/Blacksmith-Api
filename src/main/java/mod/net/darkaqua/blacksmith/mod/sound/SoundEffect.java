package net.darkaqua.blacksmith.mod.sound;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.util.ResourceLocation;

/**
 * Created by cout970 on 26/01/2016.
 */
public class SoundEffect extends PositionedSound {

    public SoundEffect(ResourceLocation soundResource, float volume, float pitch, boolean repeat, int repeatDelay, ISound.AttenuationType attenuationType, float xPosition, float yPosition, float zPosition) {
        super(soundResource);
        this.volume = volume;
        this.pitch = pitch;
        this.xPosF = xPosition;
        this.yPosF = yPosition;
        this.zPosF = zPosition;
        this.repeat = repeat;
        this.repeatDelay = repeatDelay;
        this.attenuationType = attenuationType;
    }
}
