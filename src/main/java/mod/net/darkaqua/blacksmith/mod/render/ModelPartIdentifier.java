package net.darkaqua.blacksmith.mod.render;

import net.darkaqua.blacksmith.api.render.model.IModelPartIdentifier;

/**
 * Created by cout970 on 14/12/2015.
 */
public class ModelPartIdentifier implements IModelPartIdentifier {

    protected String modID;
    protected int modelID;

    public ModelPartIdentifier(String modID, int modelID) {
        this.modID = modID;
        this.modelID = modelID;
    }

    public String getModID() {
        return modID;
    }

    public int getModelID() {
        return modelID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelPartIdentifier)) return false;

        ModelPartIdentifier that = (ModelPartIdentifier) o;

        if (modelID != that.modelID) return false;
        return !(modID != null ? !modID.equals(that.modID) : that.modID != null);

    }

    @Override
    public int hashCode() {
        int result = modID != null ? modID.hashCode() : 0;
        result = 31 * result + modelID;
        return result;
    }
}
