package net.darkaqua.blacksmith.mod.config;

import net.darkaqua.blacksmith.api.config.ConfigurationFactory;
import net.darkaqua.blacksmith.api.config.IConfiguration;

import java.io.File;

/**
 * Created by cout970 on 06/12/2015.
 */
public class BS_ConfigurationFactory extends ConfigurationFactory {

    private BS_ConfigurationFactory(){}

    public static void init(){
        INSTANCE = new BS_ConfigurationFactory();
    }

    @Override
    protected IConfiguration createConfiguration(File config, String version, boolean caseSensitiveCustomCategories) {
        return new ConfigurationWrapper(config, version, caseSensitiveCustomCategories);
    }
}
