package net.darkaqua.blacksmith.api.sound;

import net.darkaqua.blacksmith.api.registry.StaticAccess;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;

/**
 * Created by cout970 on 24/01/2016.
 */
public interface ISoundEffect {

    ResourceReference getSoundReference();

    boolean canRepeat();

    int getRepeatDelay();

    float getVolume();

    float getPitch();

    Vect3d getPosition();

    AttenuationType getAttenuationType();

    default void play() {
        StaticAccess.GAME.getSoundHandler().playSound(this);
    }

    enum AttenuationType {
        NONE(0),
        LINEAR(2);

        private int type;

        AttenuationType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }
    }
}
