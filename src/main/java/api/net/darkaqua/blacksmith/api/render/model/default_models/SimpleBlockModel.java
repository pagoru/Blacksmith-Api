package net.darkaqua.blacksmith.api.render.model.default_models;

import javafx.util.Pair;
import net.darkaqua.blacksmith.api.render.TextureLocation;
import net.darkaqua.blacksmith.api.render.model.*;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vector3i;
import net.darkaqua.blacksmith.api.util.Vector4d;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cout970 on 28/11/2015.
 */
public class SimpleBlockModel implements IBlockModel {

    private String name;
    private TextureLocation textureLocation;

    public SimpleBlockModel(String name, TextureLocation textureLocation){
        this.name = name;
        this.textureLocation = textureLocation;
    }

    @Override
    public String getModelName() {
        return name;
    }

    @Override
    public IBlockModel getParent() {
        return null;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public Display getDisplay(RenderPlace place) {
        return null;
    }

    @Override
    public List<Pair<String, TextureLocation>> getTextures() {
        List<Pair<String, TextureLocation>> list = new ArrayList<>(1);
        list.add(new Pair<>("all", textureLocation));
        return list;
    }

    @Override
    public List<IModelElement> getElements() {
        List<IModelElement> list = new ArrayList<>(1);
        list.add(new IModelElement() {
            @Override
            public Vector3i getStartPoint() {
                return new Vector3i(0,0,0);
            }

            @Override
            public Vector3i getEndPoint() {
                return new Vector3i(16, 16, 16);
            }

            @Override
            public IModelRotation getRotation() {
                return null;
            }

            @Override
            public boolean shouldRenderShadows() {
                return true;
            }

            @Override
            public IModelFace getFace(Direction dir) {
                return new IModelFace() {
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
