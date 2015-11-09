package net.darkaqua.blacksmith.api.util;

import org.apache.logging.log4j.Level;

/**
 * Created by cout970 on 08/11/2015.
 */
public abstract class Log {

    protected static Log INSTANCE;

    public static void error(String s) {
        INSTANCE.logError(s);
    }

    public static void debug(Object s) {
        INSTANCE.logDebug(s);
    }

    public static void info(String s) {
        INSTANCE.logInfo(s);
    }

    public static void warn(String s) {
        INSTANCE.logWarn(s);
    }

    public static void raw(String name, Level l, String s) {
        INSTANCE.logRaw(name, l, s);
    }

    protected abstract void logError(String s);
    protected abstract void logDebug(Object s);
    protected abstract void logInfo(String s);
    protected abstract void logWarn(String s);
    protected abstract void logRaw(String name, Level l, String s);
}
