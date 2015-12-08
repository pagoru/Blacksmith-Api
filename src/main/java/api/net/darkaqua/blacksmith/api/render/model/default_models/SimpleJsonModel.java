package net.darkaqua.blacksmith.api.render.model.default_models;

import javafx.util.Pair;
import net.darkaqua.blacksmith.api.render.ResourceReference;
import net.darkaqua.blacksmith.api.render.model.json.*;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vector3i;
import net.darkaqua.blacksmith.api.util.Vector4d;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cout970 on 28/11/2015.
 */
public class SimpleJsonModel implements IJsonModel {

    private String name;
    private ResourceReference resourceReference;

    public SimpleJsonModel(String name, ResourceReference resourceReference){
        this.name = name;
        this.resourceReference = resourceReference;
    }

    @Override
    public String getModelName() {
        return name;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public JsonRenderDisplay getDisplay(JsonRenderPlace place) {
        return null;
    }

    @Override
    public List<Pair<String, ResourceReference>> getTextures() {
        List<Pair<String, ResourceReference>> list = new ArrayList<>(1);
        list.add(new Pair<>("all", resourceReference));
        return list;
    }

    @Override
    public List<IJsonModelElement> getElements() {
        List<IJsonModelElement> list = new ArrayList<>(1);
        list.add(new IJsonModelElement() {
            @Override
            public Vector3i getStartPoint() {
                return new Vector3i(0,0,0);
            }

            @Override
            public Vector3i getEndPoint() {
                return new Vector3i(16, 16, 16);
            }

            @Override
            public IJsonModelRotation getRotation() {
                return null;
            }

            @Override
            public boolean shouldRenderShadows() {
                return true;
            }

            @Override
            public IJsonModelFace getFace(Direction dir) {
                return new IJsonModelFace() {
                    @Override
                    public Vector4d getUV() {
                        return new Vector4d(0, 0, 16, 16);
                    }

                    @Override
                    public String getTextureID() {
                        return "all";
                    }

                    @Override
                    public Direction getCullFace() {
                        return null;
                    }

                    @Override
                    public int getTextureRotation() {
                        return 0;
                    }
                };
            }
        });
        return list;
    }
}
