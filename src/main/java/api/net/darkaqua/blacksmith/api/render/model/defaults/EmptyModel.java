package net.darkaqua.blacksmith.api.render.model.defaults;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.render.model.IModelPart;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.render.model.RenderTransformation;
import net.darkaqua.blacksmith.api.util.ResourceReference;

import java.util.List;

/**
 * Created by cout970 on 21/12/2015.
 */
public class EmptyModel implements IRenderModel {

    @Override
    public String getName() {
        return "empty";
    }

    @Override
    public List<ResourceReference> getTextures() {
        return Lists.newArrayList();
    }

    @Override
    public List<IModelPart> getSubParts() {
        return Lists.newArrayList();
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public RenderTransformation getTransformation(RenderPlace place) {
        return null;
    }
}
