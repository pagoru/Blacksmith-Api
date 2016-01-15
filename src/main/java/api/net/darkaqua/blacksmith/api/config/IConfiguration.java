package net.darkaqua.blacksmith.api.config;

import java.io.File;

/**
 * Created by cout970 on 06/12/2015.
 */
public interface IConfiguration {

    String CATEGORY_GENERAL = "general";

    void load();

    boolean hasChanged();

    void save();

    File getConfigFile();

    String getString(String category, String key, String defaultValue, String comment);

    int getInteger(String category, String key, int defaultValue, String comment);

    boolean getBoolean(String category, String key, boolean defaultValue, String comment);

    double getDouble(String category, String key, double defaultValue, String comment);

    String[] getStringArray(String category, String key, String[] defaultValue, String comment);

    int[] getIntegerArray(String category, String key, int[] defaultValue, String comment);

    boolean[] getBooleanArray(String category, String key, boolean[] defaultValue, String comment);

    double[] getDoubleArray(String category, String key, double[] defaultValue, String comment);

    String getString(String category, String key, String defaultValue, String comment, String[] validValues);

    int getInteger(String category, String key, int defaultValue, String comment, int min, int max);

    double getDouble(String category, String key, double defaultValue, String comment, double min, double max);

    String[] getStringArray(String category, String key, String[] defaultValue, String comment, String[] validValues);

    int[] getIntegerArray(String category, String key, int[] defaultValue, String comment, int min, int max);

    double[] getDoubleArray(String category, String key, double[] defaultValue, String comment, double min, double max);
}
