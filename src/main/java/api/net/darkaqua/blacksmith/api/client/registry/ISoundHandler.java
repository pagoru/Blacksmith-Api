package net.darkaqua.blacksmith.api.client.registry;

import net.darkaqua.blacksmith.api.client.sound.ISoundEffect;

/**
 * Created by cout970 on 24/01/2016.
 */
public interface ISoundHandler {

    void playSound(ISoundEffect sound);

    boolean isSoundPlaying(ISoundEffect sound);

    void stopSound(ISoundEffect sound);
}
