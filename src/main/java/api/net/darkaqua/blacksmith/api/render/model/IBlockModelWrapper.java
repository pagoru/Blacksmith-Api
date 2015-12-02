package net.darkaqua.blacksmith.api.render.model;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IBlockModelWrapper {

    IBlockModel getBlockModel();

    int getRotationX();

    int getRotationY();

    boolean useUVLock();

    int getWeight();
}
