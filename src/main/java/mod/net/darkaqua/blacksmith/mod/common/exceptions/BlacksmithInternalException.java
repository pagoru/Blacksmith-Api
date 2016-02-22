package net.darkaqua.blacksmith.mod.common.exceptions;

/**
 * Created by cout970 on 26/11/2015.
 */
public class BlacksmithInternalException extends RuntimeException {

    public BlacksmithInternalException() {
    }

    public BlacksmithInternalException(String message) {
        super(message);
    }

    public BlacksmithInternalException(Throwable error) {
        super(error);
    }
}
