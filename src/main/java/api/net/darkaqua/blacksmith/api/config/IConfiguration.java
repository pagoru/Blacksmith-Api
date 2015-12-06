package net.darkaqua.blacksmith.api.config;

/**
 * Created by cout970 on 06/12/2015.
 */
public interface IConfiguration {


    void load();

    boolean hasChanged();

    void save();
}
