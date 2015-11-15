package net.darkaqua.blacksmith.mod.entity;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by cout970 on 15/11/2015.
 */
public class EntityPlayerWrapper extends EntityWrapper implements IPlayer {

    private EntityPlayer player;

    public EntityPlayerWrapper(EntityPlayer player) {
        super(player);
    }

    public EntityPlayer getPlayer(){
        return player;
    }
}
