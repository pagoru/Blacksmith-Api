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

    @Override
    public void load() {
        super.load();
    }

    @Override
    public boolean hasChanged() {
        return super.hasChanged();
    }

    @Override
    public void save() {
        super.save();
    }

    @Override
    public File getConfigFile() {
        return super.getConfigFile();
    }

    @Override
    public String getString(String category, String key, String defaultValue, String comment){
        return super.getString(key, category, defaultValue, comment);
    }

    @Override
    public int getInteger(String category, String key, int defaultValue, String comment) {
        return get(category, key, defaultValue, comment).getInt();
    }

    @Override
    public boolean getBoolean(String category, String key, boolean defaultValue, String comment){
        return super.getBoolean(key, category, defaultValue, comment);
    }

    @Override
    public double getDouble(String category, String key, double defaultValue, String comment) {
        return get(category, key, defaultValue, comment).getDouble();
    }

    @Override
    public String[] getStringArray(String category, String key, String[] defaultValue, String comment) {
        return getStringList(key, category, defaultValue, comment);
    }

    @Override
    public int[] getIntegerArray(String category, String key, int[] defaultValue, String comment) {
        return get(category, key, defaultValue, comment).getIntList();
    }

    @Override
    public boolean[] getBooleanArray(String category, String key, boolean[] defaultValue, String comment) {
        return get(category, key, defaultValue, comment).getBooleanList();
    }

    @Override
    public double[] getDoubleArray(String category, String key, double[] defaultValue, String comment) {
        return get(category, key, defaultValue, comment).getDoubleList();
    }

    @Override
    public String getString(String category, String key, String defaultValue, String comment, String[] validValues){
        return super.getString(key, category, defaultValue, comment, validValues);
    }

    @Override
    public int getInteger(String category, String key, int defaultValue, String comment, int min, int max) {
        return getInt(key, category, defaultValue, min, max, comment);
    }

    @Override
    public double getDouble(String category, String key, double defaultValue, String comment, double min, double max) {
        return get(category, key, defaultValue, comment, min, max).getDouble();
    }

    @Override
    public String[] getStringArray(String category, String key, String[] defaultValue, String comment, String[] validValues) {
        return getStringList(key, category, defaultValue, comment, validValues, key);
    }

    @Override
    public int[] getIntegerArray(String category, String key, int[] defaultValue, String comment, int min, int max) {
        return get(category, key, defaultValue, comment, min, max).getIntList();
    }

    @Override
    public double[] getDoubleArray(String category, String key, double[] defaultValue, String comment, double min, double max) {
        return get(category, key, defaultValue, comment, min, max).getDoubleList();
    }
}
