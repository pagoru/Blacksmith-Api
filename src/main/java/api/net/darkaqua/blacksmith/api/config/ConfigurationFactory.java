package net.darkaqua.blacksmith.api.config;

import java.io.File;

/**
 * Created by cout970 on 06/12/2015.
 */
public abstract class ConfigurationFactory {

    protected static ConfigurationFactory INSTANCE;

    public static IConfiguration create(File config) {
        return INSTANCE.createConfiguration(config, null, false);
    }

    public static IConfiguration create(File config, String version) {
        return INSTANCE.createConfiguration(config, version, false);
    }

    public static IConfiguration create(File config, String version, boolean caseSensitiveCustomCategories) {
        return INSTANCE.createConfiguration(config, version, caseSensitiveCustomCategories);
    }

    protected abstract IConfiguration createConfiguration(File config, String version, boolean caseSensitiveCustomCategories);
}
