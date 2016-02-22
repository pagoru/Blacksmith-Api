package net.darkaqua.blacksmith.api.common.command;

/**
 * Created by cout970 on 14/01/2016.
 */
public interface IChatMessage {

    IChatMessage appendText(String text);

    IChatMessage appendMessage(IChatMessage msg);

    String getUnformattedText();

    String getFormattedText();

    IChatMessage copy();
}
