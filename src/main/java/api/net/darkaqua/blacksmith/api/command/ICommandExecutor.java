package net.darkaqua.blacksmith.api.command;

import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 14/01/2016.
 */
public interface ICommandExecutor {

    String getName();

    IChatMessage getDisplayName();

    void sendChatMessage(IChatMessage msg);

    WorldRef getWorldRef();

    Vect3d getPosition();

    //MAY BE NULL!
    IEntity getEntity();

}
