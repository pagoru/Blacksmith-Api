package com.cout970.testmod;

import net.darkaqua.blacksmith.api.common.config.util.ConfigValue;

/**
 * Created by cout970 on 09/01/2016.
 */
public class ConfigHolder {

    @ConfigValue(comment = "int amount")
    public int amount = -1;

    @ConfigValue(comment = "double size")
    public double size = 1d;

    @ConfigValue(comment = "boolean enable = true")
    public boolean enable = true;

    @ConfigValue(comment = "String name = \"unknown\"")
    public String name = "unknown";
}
