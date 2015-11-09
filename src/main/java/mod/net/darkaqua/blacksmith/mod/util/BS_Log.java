package net.darkaqua.blacksmith.mod.util;

import net.darkaqua.blacksmith.api.util.Log;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class BS_Log extends Log {

    private BS_Log() {}

    public static void init(){
        INSTANCE = new BS_Log();
    }

    private static String name = "Blacksmith|Log";

    @Override
    protected void logError(String s) {
        FMLLog.log(name, Level.ERROR, s);
    }

    @Override
    protected void logDebug(Object s) {
        FMLLog.log(name + "[DEBUG]", Level.INFO, String.valueOf(s));
    }

    @Override
    protected void logInfo(String s) {
        FMLLog.log(name, Level.INFO, s);
    }

    @Override
    protected void logWarn(String s) {
        FMLLog.log(name, Level.WARN, s);
    }

    @Override
    protected void logRaw(String name, Level l, String s) {
        FMLLog.log(name, l, s);
    }
}
