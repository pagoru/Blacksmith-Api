package net.darkaqua.blacksmith.api.common.command;

/**
 * Created by cout970 on 15/01/2016.
 */
public abstract class ChatMessageFactory {

    protected static ChatMessageFactory INSTANCE;

    public static IChatMessage createChatMessage(String text){
        return INSTANCE.newChatMessage(text);
    }

    protected abstract IChatMessage newChatMessage(String text);
}
