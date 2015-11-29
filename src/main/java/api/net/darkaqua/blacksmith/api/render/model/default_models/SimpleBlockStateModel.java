package net.darkaqua.blacksmith.api.render.model.default_models;

import net.darkaqua.blacksmith.api.render.model.IBlockStateModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cout970 on 28/11/2015.
 */
public class SimpleBlockStateModel implements IBlockStateModel {

    private String modelName, stateName;
    private int rotX, rotY;
    boolean useUVLock;

    public SimpleBlockStateModel(String modelName, String stateName){
        this.modelName = modelName;
        this.stateName = stateName;
    }

    @Override
    public String getBlockStateName() {
        return stateName;
    }

    @Override
    public String getModelName() {
        return modelName;
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
        return false;
    }

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public List<IBlockStateModel> getAlternatives() {
        return new ArrayList<>();
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
