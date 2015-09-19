package net.darkaqua.blacksmith.mod.modloader;

import java.util.List;

import com.google.common.collect.Lists;

public class ModLoaderManager {

	public static final List<BlacksmithModContainer> loadedMods = Lists.newArrayList();

	public static void registerPlugin(BlacksmithModContainer container, Object instance) {
		loadedMods.add(container);
		//TODO register the mod in the event bus
	}

	public static String findName(Object plugin) {
		if (plugin == null)
			return null;
		for (BlacksmithModContainer o : loadedMods) {
			if (plugin.equals(o.modInstance)) {
				return o.getModId();
			}
		}
		return null;
	}

}
