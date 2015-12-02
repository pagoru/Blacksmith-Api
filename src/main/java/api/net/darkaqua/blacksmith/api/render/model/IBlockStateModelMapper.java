package net.darkaqua.blacksmith.api.render.model;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IBlockStateModelMapper {

    String getModelName();

    int getRotationX();

    int getRotationY();

    boolean useUVLock();

    int getWeight();
}
