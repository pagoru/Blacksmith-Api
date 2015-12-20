package net.darkaqua.blacksmith.mod.render.util;

import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.minecraft.util.ResourceLocation;

/**
 * Created by cout970 on 14/12/2015.
 */
public class ModelIdentifier implements IModelIdentifier {

    private ResourceLocation model;
    private String variant;

    public ModelIdentifier(ResourceLocation model, String variant) {
        this.model = model;
        this.variant = variant;
    }

    @Override
    public ResourceReference getReference() {
        return new ResourceReference(model.getResourceDomain(), model.getResourcePath());
    }

    @Override
    public String getVariant() {
        return variant;
    }

    public ResourceLocation getModel() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelIdentifier)) return false;

        ModelIdentifier that = (ModelIdentifier) o;

        return !(model != null ? !model.equals(that.model) : that.model != null);
    }

    @Override
    public int hashCode() {
        return model != null ? model.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ModelIdentifier{model = " + model + "}";
    }
}
