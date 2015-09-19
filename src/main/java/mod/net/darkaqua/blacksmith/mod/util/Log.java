package net.darkaqua.blacksmith.mod.util;

import org.apache.logging.log4j.Level;

import net.minecraftforge.fml.common.FMLLog;

public class Log {
	
	public static String name = "Blacksmith";

	public void error(String s) {
		FMLLog.log(name, Level.ERROR, s);
	}

	public void debug(Object s) {
		FMLLog.log(name + "[DEBUG]", Level.INFO, String.valueOf(s));
	}

	public void info(String s) {
		FMLLog.log(name, Level.INFO, s);
	}
	
	public void warn(String s) {
		FMLLog.log(name, Level.WARN, s);
	}

	public void raw(Object s) {
		FMLLog.log(name, Level.INFO, String.valueOf(s));
	}
}
