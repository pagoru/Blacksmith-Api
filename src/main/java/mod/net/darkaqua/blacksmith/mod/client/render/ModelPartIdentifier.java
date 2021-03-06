package net.darkaqua.blacksmith.mod.client.render;

import net.darkaqua.blacksmith.api.client.render.model.IPartIdentifier;

/**
 * Created by cout970 on 14/12/2015.
 */
public class ModelPartIdentifier implements IPartIdentifier {

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

        return modelID == that.modelID
                && !(modID != null ? !modID.equals(that.modID) : that.modID != null);

    }

    @Override
    public int hashCode() {
        int result = modID != null ? modID.hashCode() : 0;
        result = 31 * result + modelID;
        return result;
    }

    @Override
    public String toString() {
        return "ModelPartIdentifier{" +
                "modID='" + modID + '\'' +
                ", modelID=" + modelID +
                '}';
    }
}
