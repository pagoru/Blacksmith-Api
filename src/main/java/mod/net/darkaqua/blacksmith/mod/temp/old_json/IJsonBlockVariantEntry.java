package net.darkaqua.blacksmith.mod.temp.old_json;

import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IJsonBlockVariantEntry {

    IModelIdentifier getModelIdentifier();

    int getRotationX();

    int getRotationY();

    boolean useUVLock();

    int getWeight();
}
