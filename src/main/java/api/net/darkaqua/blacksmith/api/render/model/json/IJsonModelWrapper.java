package net.darkaqua.blacksmith.api.render.model.json;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IJsonModelWrapper {

    IJsonModel getBlockModel();

    int getRotationX();

    int getRotationY();

    boolean useUVLock();

    int getWeight();
}
