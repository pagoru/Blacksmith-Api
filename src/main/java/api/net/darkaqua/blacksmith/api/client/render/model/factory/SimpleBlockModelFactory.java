package net.darkaqua.blacksmith.api.client.render.model.factory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.darkaqua.blacksmith.api.client.render.model.IModelFactory;
import net.darkaqua.blacksmith.api.client.render.model.IModelPart;
import net.darkaqua.blacksmith.api.client.render.model.IPartIdentifier;
import net.darkaqua.blacksmith.api.client.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.client.render.model.defaults.ModelPartBlockSide;
import net.darkaqua.blacksmith.api.client.render.model.defaults.SimpleBlockModel;
import net.darkaqua.blacksmith.api.common.modloader.IModIdentifier;
import net.darkaqua.blacksmith.api.common.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;

import java.util.List;
import java.util.Map;

/**
 * Created by cout970 on 13/03/2016.
 */
public class SimpleBlockModelFactory implements IModelFactory {

    protected Map<Direction, ResourceReference> textures;
    protected boolean singleTexture;
    protected IModIdentifier mod;

    public SimpleBlockModelFactory(IModIdentifier mod, ResourceReference texture) {
        textures = Maps.newEnumMap(Direction.class);
        for (Direction d : Direction.values()) {
            textures.put(d, texture);
        }
        singleTexture = true;
    }

    public SimpleBlockModelFactory(IModIdentifier mod, Map<Direction, ResourceReference> texture) {
        textures = Maps.newHashMap(texture);
    }

    @Override
    public Map<String, IStaticModel> createModels(IModelRegistry registry) {
        Map<String, IStaticModel> models = Maps.newHashMap();
        if (singleTexture) {
            IModelPart part = new ModelPartBlockSide(Direction.values(), textures.get(Direction.UP));
            models.put("all", new SimpleBlockModel(registry.registerModelPart(mod, part)));
        } else {
            List<IPartIdentifier> parts = Lists.newArrayList();
            for (Direction d : Direction.values()) {
                parts.add(registry.registerModelPart(mod, new ModelPartBlockSide(d, textures.get(d))));
            }
            models.put("all", new SimpleBlockModel(parts));
        }
        return models;
    }
}
