package net.darkaqua.blacksmith.api.common.command;

import net.darkaqua.blacksmith.api.common.entity.IEntity;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.WorldRef;

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
