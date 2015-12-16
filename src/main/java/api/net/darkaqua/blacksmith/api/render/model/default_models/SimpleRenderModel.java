package net.darkaqua.blacksmith.api.render.model.default_models;

import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.render.model.IModelPart;
import net.darkaqua.blacksmith.api.render.model.IModelQuad;
import net.darkaqua.blacksmith.api.render.model.IRenderModel;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.*;

/**
 * Created by cout970 on 07/12/2015.
 */
public class SimpleRenderModel implements IRenderModel {

    private List<IModelPart> parts;
    private Set<ResourceReference> textures;

    public SimpleRenderModel(){
        parts = new LinkedList<>();
        textures = new HashSet<>();
    }

    public void addModelPart(IModelPart part){
        if (part == null){
            throw new NullPointerException("null model part");
        }
        parts.add(part);
        for(IModelQuad quad : part.getQuads()){
            textures.add(quad.getTexture());
        }
    }

    @Override
    public String getName() {
        return "BasicModel";
    }

    @Override
    public List<ResourceReference> getTextures() {
        return new ArrayList<>(textures);
    }

    @Override
    public List<IModelPart> getSubParts() {
        return parts;
    }

    @Override
    public Matrix4f getTransformationMatrix(RenderPlace place) {

        if(place == RenderPlace.THIRD_PERSON || place == RenderPlace.THIRD_PERSON_LEFT_HAND || place == RenderPlace.THIRD_PERSON_RIGHT_HAND){
            Matrix4f mat = new Matrix4f();
            mat.setIdentity();
            mat.setTranslation(new Vector3f(0f, 1.5f*0.0625f, -2.75f*0.0625f));
            mat.setRotation(new Quat4f((float) Math.toRadians(5),(float) Math.toRadians(-140),(float) Math.toRadians(-20),1));
            mat.setScale(0.375f);
            return mat;
        }
        return null;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }
}
