package net.darkaqua.blacksmith.api.render.model.default_models;

import net.darkaqua.blacksmith.api.render.model.json.IJsonModel;
import net.darkaqua.blacksmith.api.render.model.json.IJsonModelWrapper;

/**
 * Created by cout970 on 28/11/2015.
 */
public class SimpleJsonModelWrapper implements IJsonModelWrapper {

    private int rotX, rotY;
    private boolean useUVLock;
    private IJsonModel model;

    public SimpleJsonModelWrapper(IJsonModel model){
        this.model = model;
    }

    @Override
    public IJsonModel getBlockModel() {
        return model;
    }

    @Override
    public int getRotationX() {
        return rotX;
    }

    @Override
    public int getRotationY() {
        return rotY;
    }

    @Override
    public boolean useUVLock() {
        return useUVLock;
    }

    @Override
    public int getWeight() {
        return 1;
    }

    public void setRotationX(int rotX) {
        this.rotX = rotX;
    }

    public void setRotationY(int rotY) {
        this.rotY = rotY;
    }

    public void setUVLock(boolean useUVLock) {
        this.useUVLock = useUVLock;
    }
}
