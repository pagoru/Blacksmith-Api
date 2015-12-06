package net.darkaqua.blacksmith.api.render.model.default_models;

import net.darkaqua.blacksmith.api.render.model.json.IBlockModel;
import net.darkaqua.blacksmith.api.render.model.json.IBlockModelWrapper;

/**
 * Created by cout970 on 28/11/2015.
 */
public class SimpleBlockModelWrapper implements IBlockModelWrapper {

    private int rotX, rotY;
    private boolean useUVLock;
    private IBlockModel model;

    public SimpleBlockModelWrapper(IBlockModel model){
        this.model = model;
    }

    @Override
    public IBlockModel getBlockModel() {
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
