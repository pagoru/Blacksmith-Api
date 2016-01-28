package net.darkaqua.blacksmith.mod.render.model;

import net.darkaqua.blacksmith.api.render.model.IModelPart;
import net.darkaqua.blacksmith.api.render.model.IModelQuad;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect2d;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.mod.util.Log;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.EnumFacing;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 27/12/2015.
 */
public class ModelPartBuilder implements IModelBuilder {

    private IModelPart model;
    private static final Map<ResourceReference, TextureAtlasSprite> textures = new HashMap<>();

    public ModelPartBuilder(IModelPart component) {
        this.model = component;
    }

    public IModelPart getModelPart() {
        return model;
    }

    @Override
    public void onTexturesLoad(TextureMap textureGetter) {
        Set<ResourceReference> differentTextures = new HashSet<>();
        differentTextures.addAll(model.getQuads().stream().map(IModelQuad::getTexture).collect(Collectors.toList()));

        for (ResourceReference res : differentTextures) {
            TextureAtlasSprite tex = textureGetter.registerSprite(MCInterface.toResourceLocation(res));
            textures.put(res, tex);
        }
    }

    @Override
    public IBakedModelPart build() {
        List<BakedQuad> generalQuads = new LinkedList<>();
        EnumMap<EnumFacing, List<BakedQuad>> quads = new EnumMap<>(EnumFacing.class);
        for (EnumFacing e : EnumFacing.values()) {
            quads.put(e, new LinkedList<>());
        }
        TextureAtlasSprite particles = null;
        try {
            for (IModelQuad s : model.getQuads()) {
                UnBakedQuad uQuad = new UnBakedQuad(MCInterface.toEnumFacing(s.getSide()));
                boolean textureError = false;

                TextureAtlasSprite sprite = textures.get(s.getTexture());
                if (sprite == null)
                    throw new IllegalStateException("Some IModelQuad(" + s + ") uses a texture(" + s.getTexture() + ") that is not provided by IModelPart(" + model + ")");

                if (particles == null) {
                    particles = sprite;
                }

                for (IModelQuad.QuadVertex v : IModelQuad.QuadVertex.values()) {
                    Vect2d in_uv = s.getUV(v);

                    if (in_uv == null)
                        throw new IllegalStateException("Invalid UV creating a IModelQuad(" + s + ") for IModelPart(" + model + ")");

                    Vect2d uv = new Vect2d(sprite.getInterpolatedU(in_uv.getX() * 16d), sprite.getInterpolatedV(in_uv.getY() * 16d));
                    if (uv.isNullVector()){
                        textureError = true;
                    }
                    Vect3d vertex = s.getVertex(v);

                    if (vertex == null)
                        throw new IllegalStateException("Invalid Vertex creating a IModelQuad(" + s + ") for IModelPart(" + model + ")");

                    uQuad.addVertex(vertex, uv);
                }
                if (textureError) {
                    Log.warn("Founded incorrect texture: TextureReference: " + s.getTexture() + ", TextureAtlasSprite: " + sprite + ", ModelQuad class: "+s.getClass()+", Model: "+model);
                }
                uQuad.setShade(s.useShade());
                if (s.getSide() == null) {
                    generalQuads.add(uQuad.bake());
                } else {
                    quads.get(MCInterface.toEnumFacing(s.getSide())).add(uQuad.bake());
                }
            }
            return new BakedModelPart(generalQuads, quads, particles);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class BakedModelPart implements IBakedModelPart {

        protected List<BakedQuad> generalQuads;
        protected EnumMap<EnumFacing, List<BakedQuad>> quads;
        protected TextureAtlasSprite particles;

        public BakedModelPart(List<BakedQuad> generalQuads, EnumMap<EnumFacing, List<BakedQuad>> quads, TextureAtlasSprite particles) {
            this.generalQuads = generalQuads;
            this.quads = quads;
            this.particles = particles;
        }

        @Override
        public List<BakedQuad> getFaceQuads(EnumFacing side) {
            return quads.get(side);
        }

        @Override
        public List<BakedQuad> getGeneralQuads() {
            return generalQuads;
        }

        @Override
        public TextureAtlasSprite getParticleTexture() {
            return particles;
        }
    }
}
