package net.darkaqua.blacksmith.api.client.render.model;

import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;

import java.util.List;

/**
 * Created by cout970 on 07/12/2015.
 */
@Implementable
public interface IStaticModel {

    List<IPartIdentifier> getParts();

    RenderTransformation getTransformation(RenderPlace place);

    IModelProperties getProperties();
}
