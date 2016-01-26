package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.registry.ISoundHandler;
import net.darkaqua.blacksmith.api.sound.ISoundEffect;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.Minecraft;

/**
 * Created by cout970 on 24/01/2016.
 */
public class SoundHandler implements ISoundHandler{

    public static final SoundHandler INSTANCE = new SoundHandler();

    @Override
    public void playSound(ISoundEffect sound) {
        Minecraft.getMinecraft().getSoundHandler().playSound(MCInterface.toSound(sound));
    }

    @Override
    public boolean isSoundPlaying(ISoundEffect sound) {
        return Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(MCInterface.toSound(sound));
    }

    @Override
    public void stopSound(ISoundEffect sound) {
        Minecraft.getMinecraft().getSoundHandler().stopSound(MCInterface.toSound(sound));
    }
}
