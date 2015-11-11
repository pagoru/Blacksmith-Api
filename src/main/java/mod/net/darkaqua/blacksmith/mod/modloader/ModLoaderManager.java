package net.darkaqua.blacksmith.mod.modloader;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.event.EventBus;

import java.util.ArrayList;
import java.util.List;

public class ModLoaderManager {

	private static final List<BlacksmithModContainer> loadedMods = Lists.newArrayList();
	private ModLoaderManager() {}

	public static void registerPlugin(BlacksmithModContainer container, Object instance) {
		loadedMods.add(container);
		EventBus.registerEventListener(instance);
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

	public static List<BlacksmithModContainer> getLoadedMods(){
		return new ArrayList<BlacksmithModContainer>(loadedMods);
	}

}
