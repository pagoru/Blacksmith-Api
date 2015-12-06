package net.darkaqua.blacksmith.mod.config;

import net.darkaqua.blacksmith.api.config.IConfiguration;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by cout970 on 06/12/2015.
 */
public class ConfigurationWrapper extends Configuration implements IConfiguration{

    public ConfigurationWrapper(File config, String version, boolean caseSensitiveCustomCategories) {
        super(config, version, caseSensitiveCustomCategories);
    }
}
