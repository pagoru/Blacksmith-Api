package net.darkaqua.blacksmith.api.render.model;

import java.util.List;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IBlockStateModel {

    String getBlockStateName();
    String getModelName();

    int getRotationX();

    int getRotationY();

    boolean useUVLock();

    int getWeight();

    List<IBlockStateModel> getAlternatives();
}
