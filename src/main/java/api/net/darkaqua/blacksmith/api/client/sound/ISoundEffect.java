package net.darkaqua.blacksmith.api.client.sound;

import net.darkaqua.blacksmith.api.Game;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;

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
        Game.getClientHandler().getSoundHandler().playSound(this);
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
