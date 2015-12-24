package net.darkaqua.blacksmith.api.render.model;

import net.darkaqua.blacksmith.api.util.ResourceReference;

/**
 * Created by cout970 on 14/12/2015.
 */
public interface IModelIdentifier{

    ResourceReference getReference();

    String getModelName();
}
